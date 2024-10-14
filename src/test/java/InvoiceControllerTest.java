import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.InvoiceController;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IInvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
        log.info("MockMvc configuré.");
    }

    @Test
    public void createInvoice_ShouldReturnBadRequest_WhenInvoiceAmountIsNegative() throws Exception {
        // Arrange
        Invoice invalidInvoice = new Invoice(null, 10, -100, new Date(), new Date(), false, null, null);
        String jsonRequest = objectMapper.writeValueAsString(invalidInvoice);

        // Act & Assert
        mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andDo(result -> log.info("Statut de la réponse : {}", result.getResponse().getStatus()));

        log.info("Test terminé : montant de la facture négatif.");
    }

    @Test
    public void createInvoice_ShouldReturnBadRequest_WhenInvoiceAmountIsTooHigh() throws Exception {
        // Arrange
        Invoice invalidInvoice = new Invoice(null, 10, 20000, new Date(), new Date(), false, null, null);
        String jsonRequest = objectMapper.writeValueAsString(invalidInvoice);

        // Act & Assert
        mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andDo(result -> log.info("Statut de la réponse : {}", result.getResponse().getStatus()));

        log.info("Fin du test createInvoice_ShouldReturnBadRequest_WhenInvoiceAmountIsTooHigh");
    }
}
