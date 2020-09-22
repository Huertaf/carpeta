package es.caib.carpeta.back;


import es.caib.carpeta.core.CarpetaBusinessConfig;
import es.caib.carpeta.back.config.CarpetaBackConfig;
import es.caib.carpeta.back.controllers.InicioController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CarpetaBusinessConfig.class, CarpetaBackConfig.class})
@WebAppConfiguration
public class InicioControllerTests {

    @Autowired
    private InicioController inicioController;

    @Test
    public void testHandleRequestView() {
        ModelAndView modelAndView = inicioController.inicio(null,null);

        assertEquals("inicio", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());

        String nowValue = (String) modelAndView.getModel().get("now");
        assertNotNull(nowValue);
    }
}