import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CartItem extends StatelessWidget {
  const CartItem({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 8),
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      decoration: BoxDecoration(
        color: Colors.white,
        boxShadow: BoxShadowDecorator().defaultShadow(context),
        borderRadius: BorderRadius.circular(8),
      ),
      child: Row(
        children: [
          // image
          Container(
            width: 90,
            height: 90,
            decoration: BoxDecoration(
              color: Colors.grey[200],
              borderRadius: BorderRadius.circular(8),
              image: DecorationImage(
                image: NetworkImage(
                    'https://i01.appmifile.com/webfile/globalimg/id/cms/F5754513-7B21-DB5E-8CE9-C5101EED530C!800x800!85.jpg'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // title
                Text(
                  'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                  style: Theme.of(context).textTheme.titleSmall?.copyWith(
                        color: MyColors.secondary,
                      ),
                ),
                const SizedBox(height: 4),
                // price
                Text(
                  'Rp 100.000',
                  style: Theme.of(context).textTheme.labelMedium?.copyWith(
                      color: MyColors.primary, fontWeight: FontWeight.bold),
                ),
                const SizedBox(height: 8),
                // quantity
                Row(
                  children: [
                    // button minus
                    Container(
                      width: 24,
                      height: 24,
                      decoration: BoxDecoration(
                        color: Colors.grey[200],
                        borderRadius: BorderRadius.circular(4),
                      ),
                      child: Center(
                        child: Text(
                          '-',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.black,
                                  ),
                        ),
                      ),
                    ),
                    const SizedBox(width: 8),
                    // quantity
                    Text(
                      '2',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                    const SizedBox(width: 8),
                    // button plus
                    InkWell(
                      onTap: () {},
                      child: Container(
                        width: 24,
                        height: 24,
                        decoration: BoxDecoration(
                          color: Colors.grey[200],
                          borderRadius: BorderRadius.circular(4),
                        ),
                        child: Center(
                          child: Text(
                            '+',
                            style: Theme.of(context)
                                .textTheme
                                .labelMedium
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          const SizedBox(width: 16),
          // button remove
          Container(
            width: 24,
            height: 24,
            decoration: BoxDecoration(
              color: Colors.grey[200],
              borderRadius: BorderRadius.circular(4),
            ),
            child: Center(
              child: Icon(
                Icons.close,
                color: Colors.black,
                size: 16,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
