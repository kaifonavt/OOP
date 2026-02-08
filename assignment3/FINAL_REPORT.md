Endterm Project Report: Cafeteria Ordering System

Authors: M.Arthur , N.Marghulan

     1.Project Topic & Main User Flows

Topic: №6. Food Delivery / Canteen Ordering System   
Description: Ordering system for a canteen or delivery service with menu management and order tracking.  
Main User Flows: 
* Viewing the Menu - Users browse available food items, descriptions, and real-time pricing.
* Placing an Order - User picks items, receives bill including 15% tax, and selects between "Delivery"/"Pickup"/ "Dine-in" options. 


     2. Database Schema
Our system uses Relational DB to manage  order history and menu data.   
Entities & Tables : 
* Customers: Stores user profiles/data.
* Orders: Order headers, including total_price and order_type.
* menu_items: Stores available food and current stock info. 

Relationships: 
* Foreign Key Constraint: Each order is linked to customer_id. 1-1 relationship. System enforces referential integrity, this prevents order creation for non-existent customers.


    3. Architecture & Components



| Layer | Classes Included |
|---|---|
| Domain (Core) | Order, MenuItem, Customer, OrderItem, DeliveryOrder, PickupOrder, DineInOrder, Result, IOrderRepository, IMenuitemRepository, IRepository, IDB, and all custom Exceptions |
| Persistence (Data) | OrderRepository, MenuItemRepository, DatabaseConnection |
| Business (Services) | OrderService, MenuService, PaymentService |
| Reporting (Analytics) | TaxConfig (Singleton) and OrderFactory |
| UI (Controller) | CafeteriaApp, Main |

Used Patterns:
* Singleton - TaxConfig for centralized tax management.
* Builder - Order.builder() to create complex objects with parameters.
* Factory - OrderFactory to instantiate specific order types based on user selection.                                                       
 

    4. Component Principles (REP/CCP/CRP)
* CCP (Common Closure Principle) - Classes that change together(entity + service logic) are packaged together to limit the impact of changes. (e.g. MenuManagement components)
* CRP (Common Reuse Principle) - By separating menu management from ordering, we ensure that a user of the menu component doesn't have to depend on unnecessary ordering dependencies.
* REP (Release/Reuse Equivalency Principle) - Each component, such as the BillingComponent using TaxConfig, is decoupled enough to be reused in different contexts, such as an external delivery platform.