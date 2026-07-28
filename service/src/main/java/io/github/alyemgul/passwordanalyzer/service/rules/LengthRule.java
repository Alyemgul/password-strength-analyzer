/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.service.rules;

import io.github.alyemgul.passwordanalyzer.core.Password;

public class LengthRule implements Rule {
    private final int minLength;
    private final boolean critical;

    public LengthRule(int minLength, boolean critical) {
        this.minLength = minLength;
        this.critical = critical;
    }

    @Override
    public boolean test(Password password) {
        return password.value().length() >= minLength;
    }

    @Override
    public boolean isCritical() {
        return critical;
    }
}