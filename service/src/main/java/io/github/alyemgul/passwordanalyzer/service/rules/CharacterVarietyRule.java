/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.service.rules;

import io.github.alyemgul.passwordanalyzer.core.Password;

public class CharacterVarietyRule implements Rule {
    private final boolean critical;

    public CharacterVarietyRule(boolean critical) {
        this.critical = critical;
    }

    @Override
    public boolean test(Password password) {
        String value = password.value();
        boolean hasUpper = value.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = value.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = value.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = value.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    @Override
    public boolean isCritical() {
        return critical;
    }
}
