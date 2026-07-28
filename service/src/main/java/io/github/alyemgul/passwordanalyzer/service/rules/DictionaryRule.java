/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.service.rules;

import io.github.alyemgul.passwordanalyzer.core.Password;
import io.github.alyemgul.passwordanalyzer.dictionary.Dictionary;

public class DictionaryRule implements Rule {
    private final Dictionary dictionary;
    private final boolean critical;

    public DictionaryRule(Dictionary dictionary, boolean critical) {
        this.dictionary = dictionary;
        this.critical = critical;
    }

    @Override
    public boolean test(Password password) {
        return !dictionary.contains(password);
    }

    @Override
    public boolean isCritical() {
        return critical;
    }
}