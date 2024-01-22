package org.oludayofinance.stockui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

public class ChartApplication  extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
//        applicationContext = SpringApplication.run(ChartApplication.class);
        applicationContext = new SpringApplicationBuilder(StockUiApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
    applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();
    }
    static  class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }


        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
