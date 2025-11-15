🛒 E-Kart REST API
Welcome to the E-Kart API, a backend service for managing users, products, carts, orders, and feedback in an e-commerce platform. This API is built with secure JWT authentication and supports CRUD operations for core entities.

🔐 Authentication
All endpoints require a valid JWT Bearer Token and session cookie (JSESSIONID). Include them in your request headers:
Authorization: Bearer <your_token>
Cookie: JSESSIONID=<your_session_id>


📦 Cart APIs
➕ Add Item to Cart
POST /e-kart/cart/add?userId={userId}&productId={productId}&quantity={quantity}
Adds a product to a user's cart.
curl --location --request POST 'localhost:8080/e-kart/cart/add?userId=751c7387ca66&productId=3&quantity=2000' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=<session_id>' \
--data-raw '{}'

🛒 View Cart
GET /e-kart/cart/{userId}
Retrieves all items in the user's cart.
curl --location --request GET 'localhost:8080/e-kart/cart/751c7387ca66' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'


💬 Feedback APIs
✍️ Submit Feedback
POST /e-kart/feedback/submit?userId={userId}&productId={productId}
Submit feedback for a product.
curl --location --request POST 'localhost:8080/e-kart/feedback/submit?userId=751c7387ca66&productId=3' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=<session_id>' \
--data-raw '{
  "comment": "This is one of best project that I used, I liked the project & it is good as expected"
}'

👤 Get Feedback by User
GET /e-kart/feedback/user/{userId}
curl --location --request GET 'localhost:8080/e-kart/feedback/user/751c7387ca66' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'

📦 Get Feedback by Product
GET /e-kart/feedback/product/{productId}
curl --location --request GET 'localhost:8080/e-kart/feedback/product/3' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'

📜 Order APIs
🧾 Place Order
POST /e-kart/orders/add?userId={userId}
curl --location --request POST 'localhost:8080/e-kart/orders/add?userId=751c7387ca66' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'

📚 Order History
GET /e-kart/orders/history?userId={userId}
curl --location --request GET 'localhost:8080/e-kart/orders/history?userId=751c7387ca66' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'

🛍️ Product APIs
📦 Get All Products
GET /e-kart/products/all
curl --location --request GET 'localhost:8080/e-kart/products/all' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'

👥 User APIs
📝 Register User
POST /e-kart/user/register
curl --location --request POST 'https://ekart-book-store.onrender.com/e-kart/user/register' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--data-raw '{
  "username": "test1",
  "email": "test1@gmail.com",
  "password": "test1"
}'


👥 Get All Users
GET /e-kart/user/all
curl --location --request GET 'localhost:8080/e-kart/user/all' \
--header 'Authorization: Bearer <token>' \
--header 'Cookie: JSESSIONID=<session_id>'



🧪 Sample Basic Auth
curl --location --request GET 'https://ekart-book-store.onrender.com/e-kart/products/all' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Content-Type: application/json'



🛠️ Notes
- Replace <token> and <session_id> with actual values.
- All endpoints are protected and require valid authentication.
- Ensure the server is running locally (localhost:8080) or deployed (https://ekart-book-store.onrender.com).
- Use Content-Type: application/json for all POST requests with a body.
