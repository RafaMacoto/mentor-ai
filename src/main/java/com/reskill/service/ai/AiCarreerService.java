package com.reskill.service.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AiCarreerService implements IAiCarreerService {

    private final OpenAiChatModel openAiChatModel;

    @Override
    public AiRecommendation generateCareerPlan(String goal, List<String> skills) {
        String prompt = buildPrompt(goal, skills);


        String fullText = openAiChatModel.call(prompt);

        return parseAiResponse(fullText);
    }

    private String buildPrompt(String goal, List<String> skills) {
        return """
                Você é um mentor de carreira. Gere um plano de desenvolvimento prático e personalizado.

                DADOS DO USUÁRIO
                Objetivo: %s
                Skills atuais: %s

                REGRAS DE RESPOSTA
                - Responda em português do Brasil.
                - Não use Markdown, asteriscos, hashtags, tabelas ou introduções fora do formato.
                - Escreva uma recomendação clara, com um parágrafo para cada nível.
                - Gere de 5 a 10 tarefas concretas, em ordem de prioridade.
                - Cada tarefa deve ser uma única linha e começar com um número seguido de ponto.
                - Não repita o objetivo nem as skills sem acrescentar orientação prática.

                USE EXATAMENTE ESTE FORMATO

                RECOMENDAÇÃO:
                Nível iniciante: [orientação prática]
                Nível intermediário: [orientação prática]
                Nível avançado: [orientação prática]

                ITENS DO PLANEJAMENTO:
                1. [tarefa concreta]
                2. [tarefa concreta]
                3. [tarefa concreta]
                4. [tarefa concreta]
                5. [tarefa concreta]
                """.formatted(
                goal,
                String.join(", ", skills)
        );
    }

    private AiRecommendation parseAiResponse(String fullText) {
        if (fullText == null || fullText.isEmpty()) {
            return new AiRecommendation("Sem recomendação gerada.", new ArrayList<>());
        }

        String normalized = fullText.replace("\r\n", "\n");
        String[] split = normalized.split("(?i)(?:ITENS DO PLANEJAMENTO|TAREFAS)\\s*:", 2);
        String recommendationText = cleanRecommendation(split[0]);

        List<String> items = new ArrayList<>();
        if (split.length > 1) {
            for (String line : split[1].split("\n")) {
                Matcher matcher = Pattern.compile("^\\s*(?:[-*•]|\\d+[.)])\\s+(.+?)\\s*$").matcher(line);
                if (matcher.matches()) {
                    String item = cleanInlineFormatting(matcher.group(1));
                    if (!item.isBlank()) items.add(item);
                }
            }
        }

        return new AiRecommendation(recommendationText, items);
    }

    private String cleanRecommendation(String text) {
        return text
                .replaceFirst("(?i)RECOMENDAÇÃO\\s*:", "")
                .replaceFirst("(?i)RECOMENDACAO\\s*:", "")
                .lines()
                .map(String::trim)
                .filter(line -> !line.isBlank() && !line.matches("[*_#-]+"))
                .map(line -> line.replaceFirst("^[-*_#]+\\s*", ""))
                .map(this::cleanInlineFormatting)
                .reduce((first, second) -> first + "\\n" + second)
                .orElse("Sem recomendação gerada.")
                .trim();
    }

    private String cleanInlineFormatting(String text) {
        return text.replace("**", "").replace("__", "").replace("`", "").trim();
    }
}
