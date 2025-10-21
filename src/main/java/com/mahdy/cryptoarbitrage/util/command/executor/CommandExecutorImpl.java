package com.mahdy.cryptoarbitrage.util.command.executor;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CommandExecutorImpl implements CommandExecutor {

    private final ApplicationContext applicationContext;

    private CommandLine commandLine;

    @PostConstruct
    public void init() {
        commandLine = new CommandLine(new RootCommand());
        for (Object object : applicationContext.getBeansWithAnnotation(Command.class).values()) {
            Command annotation = object.getClass().getAnnotation(Command.class);
            commandLine.addSubcommand(annotation.name(), object);
        }
    }

    @Override
    public void executeCommand(String... commandParts) {
        commandLine.execute(commandParts);
    }

    @Command
    private class RootCommand implements Runnable {
        @Override
        public void run() {
            log.warn("a sub command should be called instead of root");
        }
    }
}
