package com.example.app;

import com.example.model.tables.Game;
import com.example.model.tables.pojos.GamePojo;
import org.jooq.DSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        DSLContext create = ctx.getBean(DSLContext.class);

        Game table = Game.GAME;
        create
                .insertInto(table)
                .set(table.ID, UUID.randomUUID())
                .set(table.TITLE, "DMC")
                .execute();

        List<GamePojo> games = create
                .select()
                .from(table)
                .fetch()
                .into(GamePojo.class);

        System.out.println(games);
    }
}
