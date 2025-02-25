package lab.lhss;

import lab.lhss.admin.catalog.infrastructure.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.AbstractEnvironment;

class MainTest {
    @Test
    void testMain() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
        Assertions.assertNotNull(new Main());
        Main.main(new String[]{});
    }

}