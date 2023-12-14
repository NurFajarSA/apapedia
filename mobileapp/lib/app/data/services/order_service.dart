import 'dart:convert';

import 'package:http/http.dart' as http;

import 'package:mobileapp/app/data/models/cart.dart';
import 'package:mobileapp/app/data/models/order.dart';
import 'package:mobileapp/app/data/models/product.dart';
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';

class OrderService {
  Future<Cart> getCartByCustomerId(String userId) async {
    var url = Uri.parse("${Api.baseUrlOrder}/order/cart/$userId");
    var response = await http.get(url);
    if (response.statusCode == 200) {
      return Cart.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load cart');
    }
  }

  Future<Order> getOrderHistory() async {
    var user = TbSharedPref.getUserLogin();
    var url = Uri.parse("${Api.baseUrlOrder}/order/customer/${user!.id}");
    var response = await http.get(url);
    if (response.statusCode == 200) {
      return Order.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load order history');
    }
  }

  // order by cart
  Future<bool> orderByCart() async {
    final response =
        await http.get(Uri.parse('https://example.com/whatsit/create'));
    if (response.statusCode == 200) {
      return true;
    } else {
      throw Exception('Failed to order');
    }
  }

  // order by buy now
  Future<bool> orderByBuyNow() async {
    final response =
        await http.get(Uri.parse('https://example.com/whatsit/create'));
    if (response.statusCode == 200) {
      return true;
    } else {
      throw Exception('Failed to order');
    }
  }

  Future<bool> addCartItem(Product product) async {
    var url = Uri.parse("${Api.baseUrlOrder}/cart/cartItem/add");
    var cartId = TbSharedPref.getCartUser()?.id;
    var body = jsonEncode(<String, dynamic>{
      "cartId": cartId,
      "productId": product.id,
      "sellerId": product.sellerId,
      "productName": product.productName,
      "productPrice": product.price,
      "quantity": 1
    });
    var response = await http.post(url,
        headers: <String, String>{
          'Content-Type': 'application/json',
        },
        body: body);
    if (response.statusCode == 200) {
      return true;
    } else {
      throw Exception('Failed to add cart item');
    }
  }
}
