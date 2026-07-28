/*
 * SPDX-FileCopyrightText: 2025 Budapest University of Technology and Economics
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package io.github.alyemgul.passwordanalyzer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import io.github.alyemgul.passwordanalyzer.core.Password;
import io.github.alyemgul.passwordanalyzer.core.Strength;
import io.github.alyemgul.passwordanalyzer.service.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {

    private static class Args {
        @Parameter(names = "--password", description = "Password to check", required = true)
        String password;

        @Parameter(names = "--loglevel", description = "Log level (INFO, DEBUG, WARN, ERROR)")
        String logLevel = "INFO";
    }

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);

        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", args.logLevel.toLowerCase());

        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.debug("Starting password strength check...");

        PasswordStrengthChecker checker = PasswordStrengthChecker.createDefault();

        Strength result = checker.evaluate(new Password(args.password));
        System.out.println("Strength: " + result);
    }
}

