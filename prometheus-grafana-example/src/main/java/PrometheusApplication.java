import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PrometheusApplication {
    static private final Gauge gauge = Gauge.build()
            .name("accuracy_percent")
            .labelNames("accuracy")
            .help("Accuracy")
            .register();

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(PrometheusApplication.class);

        try {
            HTTPServer server = new HTTPServer(10001);
            DefaultExports.initialize();

            while (true) {
                Thread.sleep(10000);
                Random random = new Random();
                gauge.labels("accuracy").set(random.nextInt(20) + 81);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
