### Implementation Guide: JPA Relationships with Lombok for Beer Order System

Based on the analyzed ERD, here are the detailed instructions for implementing the relationships between `BeerOrder`, `BeerOrderLine`, and `Beer`.

---

#### 1. Relationship Mapping Overview
*   **`BeerOrder` (1) ↔ (N) `BeerOrderLine`**: A bidirectional One-to-Many relationship. `BeerOrderLine` is the owning side.
*   **`BeerOrderLine` (N) ↔ (1) `Beer`**: A Many-to-One relationship. `BeerOrderLine` holds the foreign key to `Beer`.

---

#### 2. Entity Implementation: `BeerOrder`
The `BeerOrder` entity serves as the parent container for order items.

```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    private String customerRef;
    private BigDecimal paymentAmount;
    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    // One-to-Many relationship to BeerOrderLine
    @Builder.Default
    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BeerOrderLine> beerOrderLines = new HashSet<>();
}
```

---

#### 3. Entity Implementation: `BeerOrderLine`
This is the "Many" side (owning side) for both `BeerOrder` and `Beer`.

```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeerOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    // Relationship to BeerOrder
    @ManyToOne
    @JoinColumn(name = "beer_order_id")
    private BeerOrder beerOrder;

    // Relationship to Beer
    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private Integer orderQuantity;
    private Integer quantityAllocated;
    private String status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
```

---

#### 4. Entity Implementation: `Beer`
The `Beer` entity represents the product. It can optionally have a back-reference to its order lines.

```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    private String beerName;
    private String beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    // Optional: Bidirectional mapping back to Order Lines
    @Builder.Default
    @OneToMany(mappedBy = "beer")
    private Set<BeerOrderLine> beerOrderLines = new HashSet<>();
}
```

---

#### 5. Critical Development Notes
*   **Lombok vs. JPA**: Avoid using `@Data` or `@EqualsAndHashCode` on JPA entities. They often include all fields, which can trigger `LazyInitializationException` or cause infinite recursion in bidirectional relationships. Use `@Getter` and `@Setter` instead.
*   **Collection Initialization**: Use `@Builder.Default` when initializing collections (like `HashSet`) to prevent the Lombok Builder from setting them to `null`.
*   **Cascading**: `CascadeType.ALL` on `BeerOrder` ensures that when an order is saved or deleted, its lines are handled automatically.
*   **Orphan Removal**: `orphanRemoval = true` ensures that removing a `BeerOrderLine` from the `beerOrderLines` set in `BeerOrder` will result in that line being deleted from the database.
*   **Performance**: Use `Set` instead of `List` for `@OneToMany` collections to avoid Hibernate "bag" performance issues when merging entities.