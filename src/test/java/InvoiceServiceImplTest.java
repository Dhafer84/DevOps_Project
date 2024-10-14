/*import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvoiceServiceImplTest {
    @Autowired
    private MockMvc mockMvc;


    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Phase Arrange: Initialisation d'une facture pour le test
        invoice = new Invoice();
        invoice.setIdInvoice(1L);
        invoice.setArchived(false);
        log.info("Setup terminé : Initialisation de la facture avec id = {} et archived = {}", invoice.getIdInvoice(), invoice.getArchived());
    }

    @Test
    public void testCancelInvoiceSuccessfully() {
        // Phase 1: Arrange
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));
        log.info("Phase Arrange: Facture récupérée depuis le repository.");

        // Phase 2: Act
        log.info("Phase Act: Annulation de la facture.");
        invoiceService.cancelInvoice(1L); // Méthode qui annule la facture

        // Phase 3: Assert
        log.info("Phase Assert: Vérification des résultats attendus.");
        assertTrue(invoice.getArchived(), "La facture doit être archivée après annulation.");
        verify(invoiceRepository, times(1)).save(invoice); // Vérifie que save a été appelé une fois
        log.info("Test réussi : La facture est archivée avec succès.");
    }

    @Test
    public void testCancelInvoiceNotFound() {
        // Phase 1: Arrange
        when(invoiceRepository.findById(1L)).thenReturn(Optional.empty());
        log.warn("Phase Arrange: Aucun résultat trouvé pour l'ID de la facture.");

        // Phase 2 & 3: Act & Assert
        log.info("Phase Act: Tentative d'annulation d'une facture inexistante.");
        assertThrows(NullPointerException.class, () -> {
            invoiceService.cancelInvoice(1L);
        });
        log.info("Test réussi : Une exception a été levée pour une facture inexistante.");
    }


}*/
