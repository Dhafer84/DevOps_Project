import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

        // Initialisation des objets pour les tests
        stock = new Stock();
        stock.setIdStock(1L); // Assurez-vous que la méthode setIdStock existe dans la classe Stock

        product = new Product();
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(5);
        product.setCategory(ProductCategory.ELECTRONICS);
    }

    @Test
    public void testAddProductSuccessfully() {
        logger.info("Starting test for adding product successfully");

        // Arrange
        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product result = productService.addProduct(product, 1L);

        // Assert
        logger.info("Verifying product details");
        assertProductEquals(product, result);
        assertEquals(stock, result.getStock(), "The stock associated with the product does not match the expected stock.");
    }

    @Test
    public void testAddProductStockNotFound() {
        logger.info("Starting test for adding product with stock not found");

        // Arrange
        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            productService.addProduct(product, 1L);
        });

        assertEquals("stock not found", thrown.getMessage(), "Expected message not found for stock not found exception.");
    }

    private void assertProductEquals(Product expected, Product actual) {
        assertEquals(expected.getTitle(), actual.getTitle(), "Product titles do not match.");
        assertEquals(expected.getPrice(), actual.getPrice(), "Product prices do not match.");
        assertEquals(expected.getQuantity(), actual.getQuantity(), "Product quantities do not match.");
        assertEquals(expected.getCategory(), actual.getCategory(), "Product categories do not match.");
    }
}

/*

### Scénario de Test pour `addProduct`

L'objectif de ce test est de vérifier que la méthode `addProduct` fonctionne correctement dans deux situations :

1. Lorsqu'un produit est ajouté avec succès à un stock existant.
2. Lorsqu'on essaie d'ajouter un produit à un stock qui n'existe pas.

### 1. Test d'ajout d'un produit avec succès (`testAddProductSuccessfully`)

#### Objectif
Vérifier que la méthode `addProduct` ajoute correctement un produit à un stock existant et renvoie le produit avec les bonnes propriétés.

#### Étapes
1. **Préparation des données** :
   - **Création d'un `Stock`** : Un objet `Stock` est créé et initialisé avec un ID (par exemple, `1L`).
   - **Création d'un `Product`** : Un objet `Product` est créé avec des valeurs spécifiques (titre, prix, quantité, catégorie).

2. **Simulation des comportements des dépendances** :
   - **`stockRepository.findById(1L)`** : Simule la recherche d'un stock avec l'ID `1L` en renvoyant un `Optional` contenant le `Stock` créé.
   - **`productRepository.save(any(Product.class))`** : Simule la sauvegarde d'un produit en renvoyant le produit lui-même. Cela signifie que la méthode `save` doit fonctionner comme prévu.

3. **Exécution de la méthode à tester** :
   - Appel de `productService.addProduct(product, 1L)` pour ajouter le produit au stock.

4. **Assertions** :
   - Vérifie que le produit renvoyé a les mêmes propriétés que le produit initial (titre, prix, quantité, catégorie).
   - Vérifie que le produit est associé au stock qui a été trouvé. Cela garantit que la méthode `addProduct` a correctement configuré la relation entre le produit et le stock.

### 2. Test d'ajout d'un produit avec un stock non trouvé (`testAddProductStockNotFound`)

#### Objectif
Vérifier que la méthode `addProduct` lance une exception appropriée lorsqu'on essaie d'ajouter un produit à un stock qui n'existe pas.

#### Étapes
1. **Simulation du comportement de la dépendance** :
   - **`stockRepository.findById(1L)`** : Simule la recherche d'un stock avec l'ID `1L` en renvoyant un `Optional.empty()`, ce qui indique que le stock n'a pas été trouvé.

2. **Exécution de la méthode à tester** :
   - Appel de `productService.addProduct(product, 1L)` pour tenter d'ajouter le produit au stock.

3. **Assertion** :
   - Vérifie que la méthode lance une exception de type `NullPointerException`. Cela valide que la méthode `addProduct` gère correctement le cas où le stock est introuvable.

### Conclusion
Ce scénario de test couvre deux aspects essentiels du comportement de la méthode `addProduct` :

- **Cas positif** : Ajout d'un produit réussi avec un stock valide.
- **Cas négatif** : Gestion d'une situation où le stock n'existe pas, ce qui est important pour garantir la robustesse de votre application.

En résumé, le test vérifie que votre méthode fonctionne comme prévu dans différentes conditions, ce qui est crucial pour maintenir la qualité et la fiabilité de votre code. Si vous avez d'autres questions ou si vous souhaitez approfondir certains aspects, n'hésitez pas à demander !

 */