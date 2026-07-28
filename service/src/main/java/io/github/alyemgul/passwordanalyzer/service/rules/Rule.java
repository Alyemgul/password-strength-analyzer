/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.service.rules;

import io.github.alyemgul.passwordanalyzer.core.Password;

public interface Rule {

    boolean test(Password password);

    boolean isCritical();
}
