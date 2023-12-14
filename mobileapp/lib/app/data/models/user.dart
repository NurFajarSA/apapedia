class User {
  final String id;
  String name;
  final String username;
  final String email;
  String address;
  int balance;
  String cartId;

  User({
    required this.id,
    required this.name,
    required this.username,
    required this.email,
    required this.address,
    required this.balance,
    required this.cartId,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      name: json['name'],
      username: json['username'],
      email: json['email'],
      address: json['address'],
      balance: json['balance'],
      cartId: json['cartId'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'username': username,
        'email': email,
        'address': address,
        'balance': balance,
      };
}
