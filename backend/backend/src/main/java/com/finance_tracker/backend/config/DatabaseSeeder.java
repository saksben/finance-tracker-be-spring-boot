package com.finance_tracker.backend.config;

import com.finance_tracker.backend.models.*;
import com.finance_tracker.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

// Initialize db with initial data when there is none
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;
    private final BudgetUserRepository budgetUserRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedUsers();
        seedCategories();
        seedTransactions();
        seedBudgets();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                    User.builder().name("Me").build(),
                    User.builder().name("Dad").build(),
                    User.builder().name("Mom").build(),
                    User.builder().name("Jonathan").build()
            );
            userRepository.saveAll(users);
            System.out.println("Seeded Users!");
        }
    }

    private void seedCategories() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    Category.builder().name("Food").build(),
                    Category.builder().name("Rent").build(),
                    Category.builder().name("Budget").build()
            );
            categoryRepository.saveAll(categories);
            System.out.println("Seeded Categories!");
        }
    }

    private void seedTransactions() {
        if (transactionRepository.count() == 0) {
            Transaction t1 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 4).atStartOfDay())
                    .user(userRepository.findById(1L).orElse(null))
                    .type(TransactionType.REVENUE)
                    .amount(1065.0)
                    .description("pymt")
                    .category(categoryRepository.findById(3L).orElse(null))
                    .build();

            Transaction t2 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 5).atStartOfDay())
                    .user(userRepository.findById(4L).orElse(null))
                    .type(TransactionType.REVENUE)
                    .amount(1204.0)
                    .description("pymt")
                    .category(categoryRepository.findById(3L).orElse(null))
                    .build();

            Transaction t3 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 8).atStartOfDay())
                    .user(userRepository.findById(2L).orElse(null))
                    .type(TransactionType.REVENUE)
                    .amount(2.0)
                    .description("pymt")
                    .category(categoryRepository.findById(3L).orElse(null))
                    .build();

            Transaction t4 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 11).atStartOfDay())
                    .type(TransactionType.EXPENSE)
                    .amount(3942.0)
                    .description("expenses")
                    .category(categoryRepository.findById(3L).orElse(null))
                    .build();

            Transaction t5 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 15).atStartOfDay())
                    .type(TransactionType.EXPENSE)
                    .amount(3000.0)
                    .description("rent")
                    .category(categoryRepository.findById(2L).orElse(null))
                    .build();

            Transaction t6 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 16).atStartOfDay())
                    .type(TransactionType.EXPENSE)
                    .amount(50.0)
                    .description("Pizza")
                    .category(categoryRepository.findById(1L).orElse(null))
                    .build();

            Transaction t7 = Transaction.builder()
                    .date(LocalDate.of(2024, 6, 17).atStartOfDay())
                    .user(userRepository.findById(1L).orElse(null))
                    .type(TransactionType.REVENUE)
                    .amount(1500.0)
                    .description("pymt")
                    .category(categoryRepository.findById(2L).orElse(null))
                    .build();

            transactionRepository.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7));
            System.out.println("Seeded Transactions!");
        }
    }

    private void seedBudgets() {
        if (budgetRepository.count() == 0) {
            // Personal Account
            Budget personal = Budget.builder()
                    .name("Personal Account")
                    .estimatedRevenue(2000.0f)
                    .alertOverbudget(true)
                    .alertOverAmount(true)
                    .alertAmount(1000.0f)
                    .overbudget(false)
                    .build();
            budgetRepository.save(personal);

            BudgetUser bu1 = BudgetUser.builder()
                    .budget(personal)
                    .user(userRepository.findById(1L).orElse(null))
                    .estimate(1550.0f)
                    .build();
            budgetUserRepository.save(bu1);

            BudgetCategory bc1 = BudgetCategory.builder()
                    .budget(personal)
                    .category(categoryRepository.findById(1L).orElse(null))
                    .estimate(50.0f)
                    .build();
            BudgetCategory bc2 = BudgetCategory.builder()
                    .budget(personal)
                    .category(categoryRepository.findById(2L).orElse(null))
                    .estimate(1500.0f)
                    .build();
            budgetCategoryRepository.saveAll(List.of(bc1, bc2));

            // Joint Account
            Budget joint = Budget.builder()
                    .name("Joint Account")
                    .estimatedRevenue(3870.0f)
                    .alertOverbudget(false)
                    .alertOverAmount(false)
                    .overbudget(false)
                    .build();
            budgetRepository.save(joint);

            BudgetUser bu2 = BudgetUser.builder()
                    .budget(joint)
                    .user(userRepository.findById(1L).orElse(null))
                    .estimate(1333.0f)
                    .build();
            BudgetUser bu3 = BudgetUser.builder()
                    .budget(joint)
                    .user(userRepository.findById(4L).orElse(null))
                    .estimate(1204.0f)
                    .build();
            BudgetUser bu4 = BudgetUser.builder()
                    .budget(joint)
                    .user(userRepository.findById(2L).orElse(null))
                    .estimate(1333.0f)
                    .build();
            budgetUserRepository.saveAll(List.of(bu2, bu3, bu4));

            BudgetCategory bc3 = BudgetCategory.builder()
                    .budget(joint)
                    .category(categoryRepository.findById(3L).orElse(null))
                    .estimate(3870.0f)
                    .build();
            budgetCategoryRepository.save(bc3);
            System.out.println("Seeded Budgets!");
        }
        System.out.println("Seeding complete!");
    }
}
