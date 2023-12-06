import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CardProduct extends StatelessWidget {
  const CardProduct(
      {super.key,
      required this.productName,
      required this.productPrice,
      required this.productImage});
  // parameternya nama produk, harga, dan gambar
  final String productName;
  final String productPrice;
  final String productImage;

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: MyColors.white,
        boxShadow: BoxShadowDecorator().defaultShadow(context),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Column(
        children: [
          Container(
            height: 150,
            decoration: BoxDecoration(
              image: DecorationImage(
                image: NetworkImage(productImage),
                fit: BoxFit.cover,
              ),
            ),
          ),
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  productName,
                  style: Theme.of(context).textTheme.labelMedium,
                ),
                const SizedBox(height: 4),
                Text(
                  'Rp $productPrice',
                  style: Theme.of(context).textTheme.headlineSmall,
                ),
                const SizedBox(height: 4),
                // dua button buy dan add to cart
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    // button buy
                    SecondaryButton(
                      width: MediaQuery.of(context).size.width * 0.17,
                      onPressed: () {},
                      child: const Center(
                        child: Text(
                          'Buy',
                          style: TextStyle(color: MyColors.primary),
                        ),
                      ),
                    ),
                    // button add to cart
                    PrimaryButton(
                      width: MediaQuery.of(context).size.width * 0.17,
                      onPressed: () {},
                      child: const Row(
                        children: [
                          Icon(Icons.add_shopping_cart, color: Colors.white),
                          SizedBox(width: 4),
                          Text(
                            'Cart',
                            style: TextStyle(color: Colors.white),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
