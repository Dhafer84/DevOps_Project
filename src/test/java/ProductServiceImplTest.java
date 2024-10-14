/*import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

public class ProductServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplTest.class);

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Stock stock;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Phase Arrange: Initialisation des objets pour les tests
        stock = new Stock();
        stock.setIdStock(1L); // S'assurer que la méthode setIdStock existe dans la classe Stock

        product = new Product();
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(5);
        product.setCategory(ProductCategory.ELECTRONICS);

        logger.info("Setup terminé : Stock et produit initialisés.");
    }

    @Test
    public void testAddProductSuccessfully() {
        logger.info("Test: Ajouter un produit avec succès - Démarrage");

        // Phase Arrange: Simulation du comportement des repositories
        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        logger.info("Phase Arrange: Le stock avec id {} a été trouvé, produit prêt à être ajouté.", stock.getIdStock());

        // Phase Act: Appel de la méthode addProduct
        Product result = productService.addProduct(product, 1L);
        logger.info("Phase Act: Produit ajouté avec succès.");

        // Phase Assert: Vérification du résultat
        assertProductEquals(product, result);
        assertEquals(stock, result.getStock(), "Le stock associé au produit ne correspond pas au stock attendu.");

        logger.info("Phase Assert: Toutes les assertions sont passées. Test d'ajout de produit réussi.");
    }

    @Test
    public void testAddProductStockNotFound() {
        logger.info("Test: Ajouter un produit avec stock non trouvé - Démarrage");

        // Phase Arrange: Simulation d'une recherche de stock échouée
        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        logger.warn("Phase Arrange: Stock non trouvé pour l'id 1.");

        // Phase Act & Assert: Test de l'exception levée
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            productService.addProduct(product, 1L);
        });

        assertEquals("stock not found", thrown.getMessage(), "Le message d'exception attendu ne correspond pas.");
        logger.info("Phase Assert: Exception capturée avec succès - Stock non trouvé.");
    }

    // Méthode utilitaire pour vérifier l'égalité des produits
    private void assertProductEquals(Product expected, Product actual) {
        assertEquals(expected.getTitle(), actual.getTitle(), "Les titres des produits ne correspondent pas.");
        assertEquals(expected.getPrice(), actual.getPrice(), "Les prix des produits ne correspondent pas.");
        assertEquals(expected.getQuantity(), actual.getQuantity(), "Les quantités des produits ne correspondent pas.");
        assertEquals(expected.getCategory(), actual.getCategory(), "Les catégories des produits ne correspondent pas.");
        logger.info("Les détails du produit sont conformes à ce qui est attendu.");
    }
}*/
