package com.github.kolorobot.icm;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpringELExpressionTest {
    @Test
    public void parsesExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String value = parser.parseExpression("#{T(com.github.kolorobot.icm.incident.Incident$Status).values()}",
                new TemplateParserContext()).getValue(String.class);
    }
}
