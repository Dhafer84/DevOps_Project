import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceImplTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    public void testCancelInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setArchived(false);
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(1L);

        // Assert
        verify(invoiceRepository, times(1)).save(invoice);
        assertEquals(true, invoice.getArchived(), "The invoice should be archived.");
    }
}
