class CartItem {
  final String id;
  final String productId;
  final String sellerId;
  final String productName;
  final int productPrice;
  final int quantity;

  CartItem({
    required this.id,
    required this.productId,
    required this.sellerId,
    required this.productName,
    required this.productPrice,
    required this.quantity,
  });

  factory CartItem.fromJson(Map<String, dynamic> json) {
    return CartItem(
      id: json['cartItemid'],
      productId: json['productId'],
      sellerId: json['sellerId'],
      productName: json['productName'],
      productPrice: json['productPrice'],
      quantity: json['quantity'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'productId': productId,
        'sellerId': sellerId,
        'productName': productName,
        'productPrice': productPrice,
        'quantity': quantity,
      };
}
