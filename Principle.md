This is a Java program that simulates an online marketplace. Allows users to register, log in, view products, add to cart, view cart, fund account, place orders, and exit program.

The program starts by displaying a menu with three options: Login, Register or Exit, if the user so chooses. "Login", the user will be prompted to enter a username and password. if logged in successfully The program will set the current user to the newly logged in user. and display a new menu with the option to view products Add to Cart View Cart View Inventory or exit the program

If the user selects "Register", they will be prompted to enter their username, password, name and email address. This information will be written to a file named "users.txt".

If the user selects "View items", the program will read from a file named "items.txt" and display a list of items with prices.

If the user selects By clicking "Add to Cart", the user will be prompted to enter the name and price of the item they wish to add to the cart. The program then adds the product to the user's cart and deducts the price from the balance.

If the user selects "View Cart", the program will display the list of products in the user's cart.

If the user selects "View Inventory" program will read from a file named "[username]_inventory.txt" and lists the items that the user has in inventory.

If the user selects "Purchase", the balance will be deducted from the price of the products in the user's cart. If deducted, the remaining money will receive the product list into the file named "[username]_inventory.txt

If the user selects "account funds", the program will lead to the balance page.

If the user selects "Exit", the program will terminate.

The program uses several helper methods, such as "Login" to handle the user's login process, "Registration" to handle user registration, "viewItems" to display a list of items. "addItemToCart" to add items to the user's cart, "viewCart" to display the contents of the user's cart, and "viewInventory" to display the contents of the user's inventory.