package com.gattyspaintings.webshop;

import com.gattyspaintings.webshop.dao.CategoryDAO;
import com.gattyspaintings.webshop.dao.ProductDAO;
import com.gattyspaintings.webshop.dao.RoleDAO;
import com.gattyspaintings.webshop.dao.UserDAO;
import com.gattyspaintings.webshop.entity.Category;
import com.gattyspaintings.webshop.entity.Product;
import com.gattyspaintings.webshop.entity.Role;
import lombok.Data;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Data
@Component
public class Seeder {
    private final RoleDAO roleDAO;
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;
    private final UserDAO userDAO;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedRolesIfNoneExist();
        this.seedProductsWithCategoriesIfNoneExist();
    }

    public void seedProductsWithCategoriesIfNoneExist() {
        if(productDAO.count() == 0 && categoryDAO.count() == 0) {
            Category categoryName = new Category(
                    "Acryl",
                    "Schilderijen die gemaakt zijn met acryl verf",
                    "Acryl",
                    null
            );

            categoryDAO.save(categoryName);

            Arrays.asList(
                    new Product(
                            "Verloren in de Horizon",
                            "Een warme, dromerige compositie van rood- en rozetinten die een zonsondergang of een innerlijke gloed uitbeeldt. De golvende zwarte lijn suggereert een silhouet van een landschap of een figuur in rust, terwijl de vogels een gevoel van vrijheid oproepen.",
                            new BigDecimal("20.50"),
                            "https://photos.google.com/share/AF1QipOi2VhsG-ljY1AWq0j_WgEIhlHXKxArSuuknz9Vpz5AntaW_gLT2Pf3P7b-SG0H4A/photo/AF1QipOB0wKYOr-0S51bH5TFs9Zb1g0Go69AC6Ckqfai?key=WUVkbHpQbGliMEdlNU4teUNaOV9OeldtYk1zZ29R"
                    ),
                    new Product(
                            "Kinderlijke Verwachting",
                            "Een jong kind houdt een bordje met een zorgvuldig gepresenteerd gebakje vast. De zachte gezichtsuitdrukking en de heldere blauwe achtergrond creëren een dromerige, bijna nostalgische sfeer. Dit schilderij vangt een teder moment vol verwachting",
                            new BigDecimal("19.50"),
                            "https://photos.google.com/share/AF1QipOi2VhsG-ljY1AWq0j_WgEIhlHXKxArSuuknz9Vpz5AntaW_gLT2Pf3P7b-SG0H4A/photo/AF1QipOEYtWDvB1-SqW9vYtkXJcaMUAyiuMFFpuIaCRU?key=WUVkbHpQbGliMEdlNU4teUNaOV9OeldtYk1zZ29R"
                    )
            ).forEach(product -> {
                product.setCategory(categoryName);
                productDAO.save(product);
            });
        }
    }

    public void seedRolesIfNoneExist() {
        if(roleDAO.count() == 0) {
            roleDAO.save(new Role("USER", "A normal user"));
            roleDAO.save(new Role("ADMIN", "An administrator"));
        }
    }
}
