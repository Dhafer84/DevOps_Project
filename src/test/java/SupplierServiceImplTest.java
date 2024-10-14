/*
import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

        import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Optional;

public class SupplierServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImplTest.class);

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    private Supplier supplier;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Phase Arrange: Initialisation de l'objet Supplier pour les tests
        supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("SUP123");
        supplier.setLabel("Test Supplier");
        supplier.setSupplierCategory(SupplierCategory.CONVENTIONNE);

        logger.info("Setup terminé : Supplier initialisé avec id {}, code {}, et catégorie {}.",
                supplier.getIdSupplier(), supplier.getCode(), supplier.getSupplierCategory());
    }

    @Test
    public void testAddSupplierSuccessfully() {
        logger.info("Test: Ajouter un fournisseur avec succès - Démarrage");

        // Phase Arrange: Préparation du comportement simulé de la méthode save du repository
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        logger.info("Phase Arrange: Mock du repository prêt à sauvegarder le fournisseur.");

        // Phase Act: Appel de la méthode addSupplier
        Supplier result = supplierService.addSupplier(supplier);

        logger.info("Phase Act: Fournisseur ajouté avec succès.");

        // Phase Assert: Vérification que le fournisseur a bien été ajouté
        assertEquals(supplier.getIdSupplier(), result.getIdSupplier(), "L'ID du fournisseur ajouté ne correspond pas.");
        assertEquals(supplier.getCode(), result.getCode(), "Le code du fournisseur ne correspond pas.");
        assertEquals(supplier.getLabel(), result.getLabel(), "Le label du fournisseur ne correspond pas.");
        assertEquals(supplier.getSupplierCategory(), result.getSupplierCategory(), "La catégorie du fournisseur ne correspond pas.");

        logger.info("Phase Assert: Toutes les assertions sont vérifiées. Test d'ajout de fournisseur réussi.");
    }

    @Test
    public void testRetrieveSupplierSuccessfully() {
        logger.info("Test: Récupérer un fournisseur avec succès - Démarrage");

        // Phase Arrange: Simulation de la recherche du fournisseur par ID
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        logger.info("Phase Arrange: Le fournisseur avec id {} a été trouvé.", supplier.getIdSupplier());

        // Phase Act: Appel de la méthode retrieveSupplier
        Supplier result = supplierService.retrieveSupplier(1L);

        logger.info("Phase Act: Fournisseur récupéré avec succès.");

        // Phase Assert: Vérification que le fournisseur récupéré correspond à celui attendu
        assertEquals(supplier.getIdSupplier(), result.getIdSupplier(), "L'ID du fournisseur ne correspond pas.");
        assertEquals(supplier.getCode(), result.getCode(), "Le code du fournisseur ne correspond pas.");
        assertEquals(supplier.getLabel(), result.getLabel(), "Le label du fournisseur ne correspond pas.");
        assertEquals(supplier.getSupplierCategory(), result.getSupplierCategory(), "La catégorie du fournisseur ne correspond pas.");

        logger.info("Phase Assert: Toutes les assertions sont passées. Test de récupération de fournisseur réussi.");
    }

    @Test
    public void testDeleteSupplier() {
        logger.info("Test: Supprimer un fournisseur - Démarrage");

        // Phase Arrange: Simulation de la suppression d'un fournisseur
        doNothing().when(supplierRepository).deleteById(1L);

        logger.info("Phase Arrange: Mock du repository prêt à supprimer le fournisseur avec id 1.");

        // Phase Act: Appel de la méthode deleteSupplier
        supplierService.deleteSupplier(1L);

        logger.info("Phase Act: Fournisseur supprimé avec succès.");

        // Phase Assert: Vérification que la méthode deleteById a été appelée une fois
        verify(supplierRepository, times(1)).deleteById(1L);

        logger.info("Phase Assert: Suppression du fournisseur vérifiée.");
    }

    @Test
    public void testRetrieveSupplierNotFound() {
        logger.info("Test: Récupérer un fournisseur non trouvé - Démarrage");

        // Phase Arrange: Simulation d'un fournisseur non trouvé
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());

        logger.warn("Phase Arrange: Fournisseur non trouvé pour l'id 1.");

        // Phase Act & Assert: Vérification que l'exception est bien levée
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            supplierService.retrieveSupplier(1L);
        });

        assertEquals("Invalid user Id:1", thrown.getMessage(), "Le message d'exception attendu ne correspond pas.");
        logger.info("Phase Assert: Exception capturée avec succès - Fournisseur non trouvé.");
    }
}*/
