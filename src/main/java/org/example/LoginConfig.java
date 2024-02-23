package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig implements CommandLineRunner {
  @Value("${token}")
  private String BOT_TOKEN;

  @Override
  public void run(final String... args) {

      JDA jda = JDABuilder.createDefault(BOT_TOKEN)
          .setRawEventsEnabled(true)
          .enableIntents(GatewayIntent.MESSAGE_CONTENT)
          .addEventListeners(new SampleMessageLisner())
          .build();

      jda.updateCommands().queue();
  }
}
