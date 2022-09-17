import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PrometheusApplication {
    static private final Counter counter = Counter.build()
            .name("request_counter")
            .labelNames("request")
            .help("요청 횟수")
            .register();

    static private final Gauge gauge = Gauge.build()
            .name("accuracy_percent")
            .labelNames("accuracy")
            .help("Accuracy")
            .register();

    static private final Histogram histogram = Histogram.build()
            .name("requests_time")
            .labelNames("request")
            .buckets(0.1, 0.3, 0.5, 1.0)
            .help("요청")
            .register();

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(PrometheusApplication.class);

        try {
            HTTPServer server = new HTTPServer(10001);
            DefaultExports.initialize();

            while (true) {
                Random random = new Random();
                Histogram.Timer startTimer = histogram.labels("request").startTimer();
                Thread.sleep(random.nextInt(1000));
                counter.labels("request").inc(random.nextInt(10));
                startTimer.observeDuration();
                gauge.labels("accuracy").set(random.nextInt(20) + 81);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
