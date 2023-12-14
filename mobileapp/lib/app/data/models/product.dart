import 'dart:convert';

class Product {
  final String id;
  final String sellerId;
  final int price;
  final String productName;
  final String productDescription;
  final int stock;
  final String image;

  Product({
    required this.id,
    required this.sellerId,
    required this.price,
    required this.productName,
    required this.productDescription,
    required this.stock,
    required this.image,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'],
      sellerId: json['sellerId'],
      price: json['price'],
      productName: json['productName'],
      productDescription: json['productDescription'],
      stock: json['stock'],
      image: json['image'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'sellerId': sellerId,
        'price': price,
        'productName': productName,
        'productDescription': productDescription,
        'stock': stock,
        'image': image,
      };

  static Future<List<Product>> fromJsonList(String body) {
    return Future.value(
        List<Product>.from(jsonDecode(body).map((x) => Product.fromJson(x))));
  }
}
