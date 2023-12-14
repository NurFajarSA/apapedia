import 'package:mobileapp/app/modules/cart/widgets/cart_item.dart';

class Cart {
  final String id;
  final String customerId;
  final int totalPrice;
  final List<CartItem> listCartItem;

  Cart({
    required this.id,
    required this.customerId,
    required this.totalPrice,
    required this.listCartItem,
  });

  factory Cart.fromJson(Map<String, dynamic> json) {
    return Cart(
      id: json['cartId'],
      customerId: json['customerId'],
      totalPrice: json['totalPrice'],
      listCartItem: json['listCartItem'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'customerId': customerId,
        'totalPrice': totalPrice,
        'listCartItem': listCartItem,
      };
}
