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
            Category acryl = new Category(
                    "Acryl",
                    "Acrylverf is een veelzijdige en sneldrogende verfsoort op waterbasis. Het wordt gekenmerkt door zijn intense kleuren.",
                    "Acryl",
                    null
            );

            categoryDAO.save(acryl);

            Arrays.asList(
                    new Product(
                            "Verloren in de Horizon",
                            "Een warme, dromerige compositie van rood- en rozetinten die een zonsondergang of een innerlijke gloed uitbeeldt. De golvende zwarte lijn suggereert een silhouet van een landschap of een figuur in rust, terwijl de vogels een gevoel van vrijheid oproepen.",
                            new BigDecimal("49.99"),
                            "https://lh3.googleusercontent.com/pw/AP1GczPDLgKrO5XBPfpglLKKRbj8GSZoqr-Nr-aM0Gqg4yYUsJbY_qfTnaEGufGu16puOk1LlrUbX_Ij6SqnOukdNuxgpGi26wkcOewrNrDKHRoCfxA1fdpgRjEGbmOY9rBwG375-fF_GwQsjcmXxpsIWO_Aeg=w1437-h1073-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Kinderlijke Verwachting",
                            "Een jong kind houdt een bordje met een zorgvuldig gepresenteerd gebakje vast. De zachte gezichtsuitdrukking en de heldere blauwe achtergrond creëren een dromerige, bijna nostalgische sfeer. Dit schilderij vangt een teder moment vol verwachting",
                            new BigDecimal("40.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczNNANpIewxcML-uMo6ICjo1nvZAhgEHobeBCs95JncqxfwBtChNGA39diQjUNk7JumSxPeUXq8k7kzIHOeEF8_G1DjEcgzyJga0gciJfRnIzT2bgD1gTSjGQDkWQWlVTXDoM_9tacAjf-rsoPUIDVQnmw=w919-h1503-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Eindeloze Horizon",
                            "Een kalme zee reikt tot de horizon, waar lucht en water in blauwtinten samensmelten. Zachte wolken drijven boven de golven en stralen rust uit. Dit schilderij vangt de oneindige schoonheid van de oceaan en nodigt uit tot ontspanning en dagdromen.",
                            new BigDecimal("49.99"),
                            "https://lh3.googleusercontent.com/pw/AP1GczPDGciqQ3Jv70mpFF8u41kQ44LPQm3Ww6RJc8jfJuWzAtratm2eJUOvT3LqGLZaqnrJIK-RDbhM5BGcp9tmhBYrIRL_o8ICqD4r_tklGiY8Qe_fz61zrpeLLxWAw1xphiJ-4KPt6y5xKWXz63imLmshoA=w1437-h1110-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Fluisterende melodie",
                            "Een kind blaast op een fluit terwijl een groene papegaai dichtbij luistert, alsof ze samen een geheime melodie delen. De expressieve blik van het kind en de levendige kleuren creëren een gevoel van mysterie en verbinding.",
                            new BigDecimal("30.50"),
                    "https://lh3.googleusercontent.com/pw/AP1GczMJp8YBeld_DSf__Rs4bSVbSFtr5UxaaB1fzuiR3QdBc3OOA-rwwjGdYEQx-761eW2Xtl2LHw91FaB4kuxQ3WPb35R6U0yHKUHddfo9ufGsSPL2CIg-CKXzbW61_d7Gcc9q2ETw3DiMERwdY0-B9MgcPw=w1040-h1503-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Intricate Doodles",
                            "Een abstract kunstwerk met een verloop van rood naar geel op de achtergrond. Het schilderij is gevuld met ingewikkelde zwarte lijnpatronen en krabbels, waaronder spiralen, lussen en diverse organische vormen.",
                            new BigDecimal("55.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczO1NDwMOIY2PdOohmh43dsPyvtCCPNHBX2FpjvurRvbLPIiggXJeDjKAjdZsz2wpnSTPMK4kc_vnCYGw3-GjPUUAf_Ue86bqfePXJnfKsx50g3uI-jo4vv9OQMSXgONUjtaxYRvvHIiooS4NKBD7Z-eFQ=w1437-h1125-s-no-gm?authuser=0"
                    )
            ).forEach(product -> {
                product.setCategory(acryl);
                productDAO.save(product);
            });

            Category aquarel = new Category(
                    "Aquarel",
                    "Aquarelverf is een transparante waterverf die bekend staat om zijn lichte, dromerige uitstraling.",
                    "Aquarel",
                    null
            );

            categoryDAO.save(aquarel);

            Arrays.asList(
                    new Product(
                            "Oerkracht",
                            "De wilde penseelstreken en aardse tinten doen denken aan primitieve grotschilderingen, alsof een verborgen oerverhaal wordt verteld.",
                            new BigDecimal("50.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczM6UfiZLAVw64x8uk_11I9DcslfABCqq66ea9AfYtxxj-m88nizqKNj55p_EvQEzEcW6FyB07mB6N0ZkueKSCoIkkCU3INa7NAhJ2Xup6c2PH13Ijl_hn3Y2PIu-jpsE9vaYeG1jA6_D8-edoywGvMtRg=w1437-h1069-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Kleurrijke Dageraad",
                            "Een levendig schilderij van een zonsopgang boven een veld, met warme rood- en oranje tinten in de lucht en koele paarse en groene tinten op het land. De kleuren vloeien harmonieus in elkaar over.",
                            new BigDecimal("29.99"),
                            "https://lh3.googleusercontent.com/pw/AP1GczPWRr0alx01FMh_Eo_1ugzeWmB8RxUmMYpMC6ltOHYnG9GUpBYKcdUtkDypIg4dW_KEC4NK0AOAnjMtK9Twa1p1GifeX8vdsY2ip5ZkpKOdQ4u5EUsKEHCwOzCVgkQi5XFCxZ0VXzqtipRJvjZc5Apj7w=w1437-h1048-s-no-gm?authuser=0"
                    ),
                    new Product(
                           "Spiralen van Mysterie",
                            "Een intrigerend schilderij met een witte spiraal op een achtergrond van rood en paars. De spiraal, geschilderd met textuurvolle penseelstreken, creëert een hypnotiserend effect tegen de levendige kleuren.",
                            new BigDecimal("30.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczNWO_Orj3O80oiZsmRoz5VR3Xum-VEsWMhOqji1XjCCiaunQEb9eUjeXgF0ngP5bkDqJB_LS87m8yh47yt7u5RmIA-O1s5HroTCxQ6nNG6vBbm5xLVOiMelcRp2bEE-safxzGHIYIIIjcTVctTl2igNNw=w1437-h1024-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Zonneschijn in Abstractie",
                            "Een abstract aquarel schilderij met een grote, onregelmatig gevormde gele kern, omringd door een donkerblauwe achtergrond met vloeiende, organische patronen en penseelstreken. De randen van het schilderij zijn ongelijk, wat een vrije stijl suggereert.",
                            new BigDecimal("45.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczPc8KVG6jGHIbfbYLFsoCKTkFU8r9jiGHSSWEG7ayRZBK7cQvEkv_xYKMBJrShDH0srfuFIarqn6JicduLeWvANykpGQV4oDC4bkcwxOsTScEfTQBX4hL0Y9bLVBX7yC6h6T0xKxp839w7X474dD8_ipw=w1437-h872-s-no-gm?authuser=0"
                    ),
                    new Product(
                            "Blauwe Explosie",
                            "Een abstract aquarel schilderij met verschillende tinten blauw, geschilderd in een dynamische en onregelmatige splattertechniek. Een donkere, semi-cirkelvormige vorm aan de onderkant contrasteert met de lichtere blauwe tinten erboven.",
                            new BigDecimal("35.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczO9BtI1wCrS8MC_H2mhPItkdOw4_FKGiLYk69fSIJjxK59geTNUPdC_6qKNib-lo4sLmpNpnvmiZU5uEApZLlsyaAFy-Z781yLH-uuL8OMR9JiuNWLY78b0LU1r0B3_d9kn2tWxuzVt6OrmgXPJNyq5Yg=w1127-h1503-s-no-gm?authuser=0"
                    )
            ).forEach(product -> {
                product.setCategory(aquarel);
                productDAO.save(product);
            });

            Category pastelkrijt = new Category(
                    "Pastelkrijt",
                    "Pastelkrijt geeft een zacht, poederachtig effect met levendige kleuren en subtiele overgangen. De tekeningen stralen vaak een dromerige of expressieve sfeer uit",
                    "Pastelkrijt",
                    null
            );

            categoryDAO.save(pastelkrijt);

            Arrays.asList(
                    new Product(
                            "Boswandeling in de Herfst",
                            "Een schilderij van een bospad omringd door hoge bomen met groene bladeren. De grond is bedekt met bladeren in verschillende tinten bruin en geel. Het pad leidt de kijker dieper het bos in.",
                            new BigDecimal("50.50"),
                            "https://lh3.googleusercontent.com/pw/AP1GczOwAkI73WRPYtM6MvFhNNP82cZe1gdixg5wNDCa7tuFDSBCl_v8BPOyxs7BUyOkmoWk2mkvDYgn9MgY4d6fUdySnaGPKgVQy-dYgGPHX-L3uyvrgDFkjR286x1g-I_G1hvrrTM8YSyZ-gQcehepJbzY5g=w1437-h1045-s-no-gm?authuser=0"
                    )
            ).forEach(product -> {
                product.setCategory(pastelkrijt);
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
