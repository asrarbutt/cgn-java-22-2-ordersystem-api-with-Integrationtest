package de.neuefische.cgnjava222.ordersystem.shop.order;

import de.neuefische.cgnjava222.ordersystem.shop.product.Product;
import de.neuefische.cgnjava222.ordersystem.shop.product.ProductRepo;
import de.neuefische.cgnjava222.ordersystem.shop.product.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Test
    void addOrder() {
        //given
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);
        //da orderRepo.add nicht zurück gibt müssen das über verify testen
        when(productService.getProduct(1)).thenReturn(new Product(1, "Apfel"));
        when(productService.getProduct(3)).thenReturn(new Product(3, "Zitrone"));
        when(productService.getProduct(4)).thenReturn(new Product(4, "Mandarine"));

        //when
        orderService.addOrder(106, List.of(1, 3, 4));



        //then
        verify(orderRepo).addOrder(new Order(
                106,
                List.of(
                        new Product(1, "Apfel"),
                        new Product(3, "Zitrone"),
                        new Product(4, "Mandarine")
                )
        ));
    }

    @Test
    void addAndGetOrder() {
        //given
        ProductRepo productRepo = mock(ProductRepo.class);
       // ProductService productService = new ProductService(productRepo);
        ProductService productService=mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);

        OrderService orderService = new OrderService(productService, orderRepo);
        //OrderService orderService=mock(OrderService.class);


        when(orderService.getOrder(106)).thenReturn(new Order(
                106,
                List.of(
                        new Product(1, "Apfel"),
                        new Product(3, "Zitrone"),
                        new Product(4, "Mandarine")
                )
        ));


        //when
        orderService.addOrder(106, List.of(1, 3, 4));
        Order actual = orderService.getOrder(106);

        //then
        assertThat(actual)
                .isEqualTo(
                        new Order(
                                106,
                                List.of(
                                        new Product(1, "Apfel"),
                                        new Product(3, "Zitrone"),
                                        new Product(4, "Mandarine")
                                )
                        )
                );
    }

    @Test
    void addAndListOrders() {
        //given
        ProductRepo productRepo = mock(ProductRepo.class);
        ProductService productService = mock(ProductService.class);
        OrderRepo orderRepo = mock(OrderRepo.class);
        OrderService orderService = new OrderService(productService, orderRepo);

        when(orderService.listOrders()).thenReturn(List.of(
                new Order(
                        106,
                        List.of(
                                new Product(1, "Apfel"),
                                new Product(3, "Zitrone"),
                                new Product(4, "Mandarine")
                        )
                )
        ));
        //when
        orderService.addOrder(106, List.of(1, 3, 4));
        List<Order> actual = orderService.listOrders();

        //then
        List<Order> expected = List.of(
                new Order(
                        106,
                        List.of(
                                new Product(1, "Apfel"),
                                new Product(3, "Zitrone"),
                                new Product(4, "Mandarine")
                        )
                )
        );
        assertThat(actual)
                .hasSameElementsAs(expected)
                .hasSize(expected.size());
    }

    @Test
    void expectExceptionWhenReferencingNonexistingProduct() {
        //given
        ProductRepo productRepo = new ProductRepo();
        ProductService productService = new ProductService(productRepo);
        OrderRepo orderRepo = new OrderRepo();
        //OrderService orderService = new OrderService(productService, orderRepo);
        OrderService orderService=mock(OrderService.class);

        when(orderService.getOrder(107));

        //when
        try {
            orderService.addOrder(106, List.of(11));
            Assertions.fail("Expected exception was not thrown");
        } catch (NoSuchElementException e) {
            // perfect, exception was thrown
        }
    }
}