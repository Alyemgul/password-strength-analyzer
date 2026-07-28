/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.service;

import io.github.alyemgul.passwordanalyzer.core.Password;
import io.github.alyemgul.passwordanalyzer.core.Strength;
import io.github.alyemgul.passwordanalyzer.service.rules.Rule;
import io.github.alyemgul.passwordanalyzer.service.rules.CharacterVarietyRule;
import io.github.alyemgul.passwordanalyzer.service.rules.LengthRule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PasswordStrengthCheckerTest {
    @Test
    void strongPasswordPassesAllRules() {
        Rule lengthRule = new LengthRule(8, true);
        Rule varietyRule = new CharacterVarietyRule(false);
        PasswordStrengthChecker checker = new PasswordStrengthChecker(List.of(lengthRule, varietyRule));
        assertEquals(Strength.STRONG, checker.evaluate(new Password("Abcd1234!")));
    }

    @Test
    void mediumPasswordFailsNonCriticalRule() {
        Rule lengthRule = new LengthRule(8, true);
        Rule varietyRule = new CharacterVarietyRule(false);
        PasswordStrengthChecker checker = new PasswordStrengthChecker(List.of(lengthRule, varietyRule));
        assertEquals(Strength.MEDIUM, checker.evaluate(new Password("Abcd1234")));
    }

    @Test
    void weakPasswordFailsCriticalRule() {
        Rule lengthRule = new LengthRule(8, true);
        Rule varietyRule = new CharacterVarietyRule(true);
        PasswordStrengthChecker checker = new PasswordStrengthChecker(List.of(lengthRule, varietyRule));
        assertEquals(Strength.WEAK, checker.evaluate(new Password("abcd1234")));
    }
}
