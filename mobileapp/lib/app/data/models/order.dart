import 'package:mobileapp/app/data/models/order_item.dart';

class Order {
  final String id;
  final String customerId;
  final String sellerId;
  final int totalPrice;
  final int status;
  final List<OrderItem> listOrderItem;

  Order({
    required this.id,
    required this.customerId,
    required this.sellerId,
    required this.totalPrice,
    required this.status,
    required this.listOrderItem,
  });

  factory Order.fromJson(Map<String, dynamic> json) {
    return Order(
      id: json['orderId'],
      customerId: json['customerId'],
      sellerId: json['sellerId'],
      totalPrice: json['totalPrice'],
      status: json['status'],
      listOrderItem: json['listOrderItem'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'customerId': customerId,
        'sellerId': sellerId,
        'totalPrice': totalPrice,
        'status': status,
        'listOrderItem': listOrderItem,
      };
}
