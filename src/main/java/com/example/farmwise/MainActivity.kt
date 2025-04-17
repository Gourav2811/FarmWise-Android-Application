package com.example.farmwise

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.farmwise.ui.theme.FarmWiseTheme

data class Crop(val name: String, val description: String, val imageRes: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install Splash Screen before anything else
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FarmWiseTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    val crops = listOf(
        Crop("Apple",
            "\nApples thrive in temperate climates and prefer well-drained, loamy soils.\nThey require a " +
                    "cool climate with distinct seasons.\nThe ideal temperature for apple trees is between 15°C to 25°C.\n" +
                    "Apple trees need full sunlight for at least 6 hours a day to ensure good fruit production.\nThey also " +
                    "require consistent water but should not be waterlogged.\nThe soil should be slightly acidic to neutral, " +
                    "with a pH level between 6.0 to 7.0.\nProper pruning and pest control are necessary to ensure healthy " +
                    "growth and fruit yield.\nApple trees can take several years to mature and produce fruit, but with " +
                    "proper care, they can yield abundant harvests annually.",
            R.drawable.apple),
        Crop("Banana", "\nBananas thrive in hot, humid tropical climates.\nThey require rich, well-drained loamy soil with a pH between 5.5 and 7.5.\nHigh temperatures between 26°C and 30°C are ideal for growth.\nRegular irrigation is essential, especially during the dry season.\nBananas are propagated using suckers or tissue culture plants.\nThey need high nutrient inputs, including nitrogen, phosphorus, and potassium.\nMulching helps retain moisture and control weeds.\nCommon pests include banana weevils and nematodes.\nDiseases like Panama disease and Sigatoka leaf spot affect banana production.\nHarvesting is done 8 to 12 months after planting when fruits are fully grown but unripe.\n", R.drawable.banana),
        Crop("Blackgram", "\nBlackgram is a short-duration leguminous crop cultivated mainly for its protein-rich seeds.\nIt grows well in loamy soils with good drainage and moderate fertility.\nThe ideal soil pH for blackgram cultivation ranges between 6.0 and 7.5.\nIt requires moderate rainfall between 600mm and 800mm during its growth period.\nBlackgram is best suited for warm and humid climatic conditions with temperatures between 25°C and 35°C.\nIt is commonly grown as a Kharif (monsoon) crop but can also be cultivated in Rabi and summer seasons with proper irrigation.\nThis crop is a nitrogen-fixing plant that improves soil fertility by enhancing nitrogen levels.\nCommon pests affecting blackgram include aphids, pod borers, and whiteflies.\nDiseases like powdery mildew, rust, and yellow mosaic virus can reduce yield significantly.\nBlackgram matures within 70-90 days, and harvesting should be done when pods turn black and dry.\n", R.drawable.blackgram),
        Crop("Chickpea", "\nChickpea is a cool-season legume crop primarily grown for its high-protein seeds.\nIt thrives in dry, cool climates with moderate temperatures between 10°C and 30°C.\nChickpea prefers well-drained loamy or sandy soils with a pH range of 6.0 to 7.5.\nIt requires minimal rainfall, around 400mm to 600mm, and is highly drought-tolerant.\nSowing is typically done at the onset of the winter season for optimal growth.\nIt is a nitrogen-fixing crop, enriching the soil and reducing the need for synthetic fertilizers.\nCommon pests affecting chickpeas include pod borers, cutworms, and aphids.\nDiseases like Ascochyta blight, Fusarium wilt, and rust can severely impact yield.\nChickpeas mature within 90 to 120 days, depending on the variety and climatic conditions.\nHarvesting should be done when the plants turn yellow, and pods are dry for maximum yield.\n",R.drawable.chickpea),
        Crop("Coconut", "\nCoconut palms thrive in hot, humid tropical climates, typically within 20 degrees of the equator.\nThey prefer sandy loam soils with good drainage, often found near coastal regions.\nOptimal temperatures range from 27°C to 32°C, with high humidity levels.\nCoconut palms require consistent rainfall, ideally between 1,500mm and 2,500mm annually.\nThey are tolerant of saline conditions, making them suitable for coastal environments.\nPropagation is commonly done through seed nuts or seedlings.\nCommon pests include coconut mites, rhinoceros beetles, and leaf-eating caterpillars.\nDiseases like bud rot, leaf blight, and gray leaf spot can affect coconut palms.\nHarvesting typically begins 6-12 months after flowering, depending on the desired maturity.\nCoconut trees can produce fruits for 60-80 years, providing a long-term crop.\n",R.drawable.coconut),
        Crop("Coffee", "\nCoffee thrives in tropical highland climates with moderate rainfall and temperature.\n" +
                "It requires well-drained volcanic or loamy soils rich in organic matter.\n" +
                "Optimal temperatures for coffee cultivation range between 18°C and 24°C.\n" +
                "It grows best at altitudes between 600m and 2000m above sea level, depending on the variety.\n" +
                "Coffee plants need partial shade to protect them from direct sunlight and temperature extremes.\n" +
                "Regular irrigation is essential, especially during dry periods, for optimal growth and bean development.\n" +
                "Propagation is done through seeds or vegetative methods like grafting and cuttings.\n" +
                "Common pests affecting coffee include coffee borer beetles, aphids, and leaf miners.\n" +
                "Diseases such as coffee rust, leaf spot, and root rot can significantly impact coffee yield.\n" +
                "Coffee cherries take about 6 to 9 months to mature after flowering, depending on the variety.\n" +
                "Harvesting is done either by selective picking of ripe cherries or strip harvesting.\n" +
                "Post-harvest processing includes pulping, fermentation, drying, and roasting to enhance flavor.\n",
            R.drawable.coffee),
        Crop("Cotton", "\nCotton needs a warm climate with plenty of sunshine.\n" +
                "Deep black soil rich in nutrients is ideal for cotton cultivation.\n" +
                "Optimal temperatures range between 25°C and 35°C for healthy growth.\n" +
                "It requires a long frost-free period of at least 200 days.\n" +
                "Moderate rainfall between 50-100 cm is essential, but excess water can harm the crop.\n" +
                "Irrigation is necessary in dry regions to ensure proper development.\n" +
                "Cotton is typically grown from seeds sown directly into the field.\n" +
                "Common pests affecting cotton include bollworms, aphids, and whiteflies.\n" +
                "Diseases such as bacterial blight and root rot can reduce cotton yields.\n" +
                "The crop is ready for harvest when the bolls open and expose the white fiber.\n" +
                "Mechanical or manual picking is used to collect cotton fibers for processing.\n" +
                "Post-harvest processing includes ginning, spinning, and weaving for textile production.\n",
            R.drawable.cotton),
        Crop("Grapes", "\nGrapes thrive in temperate to warm climates with plenty of sunlight.\n" +
                "Well-drained loamy or sandy soil with good organic content is ideal.\n" +
                "The optimal temperature range for growth is between 15°C and 35°C.\n" +
                "Grapevines require moderate rainfall and proper irrigation management.\n" +
                "Propagation is done using cuttings or grafting methods.\n" +
                "Regular pruning is necessary to maintain vine health and fruit quality.\n" +
                "Common pests include aphids, mealybugs, and grape berry moths.\n" +
                "Diseases such as powdery mildew, downy mildew, and anthracnose affect grape production.\n" +
                "Drip irrigation is preferred to prevent waterlogging and fungal infections.\n" +
                "Grapes are harvested when they reach the desired sugar content and ripeness.\n" +
                "Post-harvest, grapes are used for fresh consumption, juice production, and winemaking.\n",
            R.drawable.grapes),
        Crop("Jute", "\nJute requires a warm and humid climate with temperatures between 24°C and 37°C.\n" +
                "High humidity and ample rainfall (150-250 cm annually) are essential for growth.\n" +
                "It thrives in well-drained alluvial soil found in flood-prone regions.\n" +
                "Sandy loam and clayey soils with good moisture retention also support jute cultivation.\n" +
                "Jute is primarily grown in riverine areas where soil replenishment occurs naturally.\n" +
                "Propagation is done through seeds, which germinate within 2-3 days in moist soil.\n" +
                "Weeding and thinning are necessary during the early growth stages.\n" +
                "Common pests include jute semiloopers, stem borers, and hairy caterpillars.\n" +
                "Diseases like stem rot, leaf spot, and root rot can affect plant health.\n" +
                "Harvesting is done 120-150 days after sowing when flowers appear and plants turn yellow.\n" +
                "After harvesting, jute stems are retted in water to extract fibers for processing.\n" +
                "Jute fibers are used in making sacks, ropes, carpets, textiles, and eco-friendly bags.\n",
            R.drawable.jute),
        Crop("Kidneybeans", "\nKidney beans grow best in warm climates with temperatures between 18°C and 30°C.\n" +
                "They require fertile, well-drained loamy or sandy soil with a pH of 6.0 to 7.0.\n" +
                "Moderate rainfall (30-40 inches annually) is ideal for proper growth and yield.\n" +
                "Excess moisture or waterlogging can lead to root rot and fungal infections.\n" +
                "Kidney beans are typically grown from seeds, which germinate within 7-10 days.\n" +
                "They require full sunlight for at least 6-8 hours a day to thrive.\n" +
                "Regular weeding and mulching help retain soil moisture and control weeds.\n" +
                "Common pests include aphids, bean beetles, and root nematodes.\n" +
                "Fungal diseases like rust, blight, and powdery mildew can affect plant health.\n" +
                "Harvesting is done 90-120 days after planting when pods turn dry and crisp.\n" +
                "After harvesting, beans are dried, shelled, and stored in airtight containers.\n" +
                "Kidney beans are rich in protein, fiber, and essential minerals, making them a staple food.\n",
            R.drawable.kidneybeans),
        Crop("Lentil", "\nLentils grow well in semi-arid regions with low to moderate rainfall.\n" +
                "They prefer sandy loam soil with good drainage and a pH range of 6.0 to 7.5.\n" +
                "Lentils are highly drought-tolerant and require minimal irrigation.\n" +
                "Temperatures between 18°C and 28°C are ideal for optimal growth.\n" +
                "They are typically grown as a cool-season crop and can withstand light frost.\n" +
                "Lentils are propagated through direct seed sowing, with seeds germinating in 7-10 days.\n" +
                "Weeding and soil aeration are necessary for better plant growth.\n" +
                "Common pests include aphids, pod borers, and cutworms, which can affect yields.\n" +
                "Fungal diseases like rust and root rot can impact plant health.\n" +
                "Lentils are ready for harvest in about 100-120 days when the pods turn yellow and dry.\n" +
                "After harvesting, they are dried and threshed to separate the seeds.\n" +
                "Lentils are rich in protein, fiber, and essential nutrients, making them a valuable food crop.\n",
            R.drawable.lentil),
        Crop("Maize", "\nMaize requires moderate rainfall and well-drained loamy soil for optimal growth.\n" +
                "It is highly adaptable and can grow in a wide range of climatic conditions.\n" +
                "The ideal temperature for maize cultivation is between 20°C and 30°C.\n" +
                "It prefers soil with a pH between 5.5 and 7.5 to ensure proper nutrient absorption.\n" +
                "Maize is a cereal crop that is widely grown for food, fodder, and industrial purposes.\n" +
                "Proper land preparation, including plowing and leveling, helps improve seed germination.\n" +
                "Timely irrigation is crucial, especially during the flowering and grain-filling stages.\n" +
                "Common pests include stem borers, armyworms, and maize weevils.\n" +
                "Diseases such as rust, leaf blight, and downy mildew can affect crop yield.\n" +
                "Maize is ready for harvest in about 90 to 120 days, depending on the variety.\n" +
                "After harvesting, maize is dried properly to reduce moisture and prevent fungal infections.\n" +
                "Maize is a staple food crop rich in carbohydrates, fiber, and essential vitamins.\n",
            R.drawable.maize),
        Crop("Mango", "\nMango thrives in tropical and subtropical climates with warm temperatures.\n" +
                "It requires well-drained alluvial or loamy soil with a pH range of 5.5 to 7.5.\n" +
                "The ideal temperature for mango cultivation is between 24°C and 30°C.\n" +
                "Mango trees require moderate irrigation, especially during the flowering and fruit-setting stages.\n" +
                "Propagation is commonly done through grafting to maintain desirable fruit characteristics.\n" +
                "Mango trees need regular pruning to maintain shape and improve fruit production.\n" +
                "Common pests affecting mango trees include mango hoppers, mealybugs, and fruit flies.\n" +
                "Diseases such as powdery mildew, anthracnose, and bacterial canker can reduce yield.\n" +
                "Fruit takes 3 to 6 months to mature after flowering, depending on the variety.\n" +
                "Harvesting is done when the fruit reaches full size and begins to ripen.\n" +
                "Mango is rich in vitamins A, C, and E, along with fiber and antioxidants.\n" +
                "The fruit is widely consumed fresh, dried, or processed into juices, jams, and pickles.\n",
            R.drawable.mango),
        Crop("Mothbeans", "\nMothbeans are well-suited for arid and semi-arid regions with minimal rainfall.\n" +
                "They are highly drought-resistant and can grow in poor, sandy, or loamy soils.\n" +
                "The ideal temperature for mothbean cultivation ranges between 25°C and 35°C.\n" +
                "This legume crop requires well-drained soil with a pH between 6.0 and 7.5.\n" +
                "Propagation is done through seeds, and germination occurs within 3 to 5 days.\n" +
                "Minimal irrigation is needed, as the crop is adapted to dryland farming.\n" +
                "Mothbeans help in nitrogen fixation, improving soil fertility for subsequent crops.\n" +
                "Common pests include aphids, jassids, and pod borers, which can affect yield.\n" +
                "Diseases like powdery mildew and bacterial blight may occur in humid conditions.\n" +
                "Harvesting is done 75 to 90 days after sowing when the pods turn brown and dry.\n" +
                "Mothbeans are rich in protein, fiber, and essential minerals, making them a nutritious food source.\n" +
                "They are commonly used in traditional Indian dishes, flour production, and animal feed.\n",
            R.drawable.mothbeans),
        Crop("Mungbeans", "\nMungbeans grow well in hot, dry climates with sandy or loamy soil.\n" +
                "They require well-drained soil with a pH between 6.2 and 7.2 for optimal growth.\n" +
                "The ideal temperature for mungbean cultivation ranges between 25°C and 35°C.\n" +
                "This legume crop is highly drought-tolerant and requires minimal irrigation.\n" +
                "Propagation is done through seeds, and germination occurs within 4 to 7 days.\n" +
                "Regular weeding is necessary to prevent competition with unwanted plants.\n" +
                "Mungbeans fix atmospheric nitrogen, improving soil fertility for future crops.\n" +
                "Common pests include whiteflies, aphids, and thrips, which can affect yield.\n" +
                "Diseases such as powdery mildew, yellow mosaic virus, and root rot may occur.\n" +
                "Harvesting is done 60 to 75 days after sowing when pods turn yellow and dry.\n" +
                "Mungbeans are an excellent source of protein, fiber, and essential nutrients.\n" +
                "They are widely used in sprouting, dal, soups, and various traditional dishes.\n",
            R.drawable.mungbean),
        Crop("Muskmelon", "\nMuskmelon thrives in hot, dry climates with plenty of sunlight.\n" +
                "It requires well-drained sandy loam soil with a pH range of 6.0 to 7.5.\n" +
                "The ideal temperature for muskmelon growth is between 25°C and 35°C.\n" +
                "Moderate irrigation is needed, but overwatering should be avoided to prevent root rot.\n" +
                "Propagation is done through seeds, and germination takes around 7 to 10 days.\n" +
                "Muskmelon vines require proper spacing for healthy growth and better fruit quality.\n" +
                "Regular weeding and mulching help retain moisture and reduce competition from weeds.\n" +
                "Common pests include aphids, fruit flies, and spider mites that can damage the crop.\n" +
                "Diseases such as powdery mildew, downy mildew, and fusarium wilt can affect yield.\n" +
                "Harvesting is done 80 to 100 days after planting when fruits develop a sweet aroma.\n" +
                "Muskmelons are rich in vitamins A and C, making them a nutritious summer fruit.\n",
            R.drawable.muskmelon),
        Crop("Orange", "\nOranges grow well in subtropical to tropical climates with moderate rainfall.\n" +
                "They require well-drained loamy or sandy soil with a pH range of 5.5 to 7.5.\n" +
                "The ideal temperature for orange cultivation is between 15°C and 30°C.\n" +
                "Regular irrigation is necessary, especially during flowering and fruiting stages.\n" +
                "Propagation is done using seeds, cuttings, or grafting for better yield and quality.\n" +
                "Oranges require proper spacing and pruning to ensure healthy tree growth and fruit production.\n" +
                "Common pests include citrus psyllids, aphids, and mites, which can affect tree health.\n" +
                "Diseases like citrus canker, greening disease, and root rot can reduce yield significantly.\n" +
                "Harvesting is done when the fruits reach full color and develop a sweet aroma.\n" +
                "Oranges are an excellent source of vitamin C, antioxidants, and dietary fiber.\n",
            R.drawable.orange),
        Crop("Papaya", "\nPapaya thrives in warm tropical and subtropical climates with plenty of sunlight.\n" +
                "It requires well-drained, fertile loamy soil with a pH between 6.0 and 6.5.\n" +
                "The ideal temperature range for papaya growth is between 25°C and 30°C.\n" +
                "Regular watering is necessary, but waterlogging should be avoided to prevent root rot.\n" +
                "Propagation is usually done through seeds, although tissue culture plants are also used.\n" +
                "Papaya plants are fast-growing and begin fruiting within 6 to 9 months of planting.\n" +
                "Common pests include aphids, fruit flies, and red spider mites, which can harm the crop.\n" +
                "Diseases such as papaya ringspot virus, powdery mildew, and anthracnose can affect yield.\n" +
                "Harvesting is done when the fruit turns yellow-green and softens slightly.\n" +
                "Papaya is rich in vitamins A and C, antioxidants, and digestive enzymes like papain.\n",
            R.drawable.papaya),
        Crop("Pigeonpeas", "\nPigeon peas are well adapted to dry and semi-arid climates.\n" +
                "They prefer light, well-drained soils with deep root zones for optimal growth.\n" +
                "The ideal temperature range for cultivation is between 18°C and 30°C.\n" +
                "Pigeon peas are drought-resistant and require minimal irrigation once established.\n" +
                "Propagation is done using seeds, and germination occurs within a week.\n" +
                "They are nitrogen-fixing legumes, which help improve soil fertility.\n" +
                "Common pests include pod borers, aphids, and blister beetles.\n" +
                "Diseases such as fusarium wilt, sterility mosaic virus, and powdery mildew can affect crops.\n" +
                "Harvesting is done when pods turn brown and dry, usually after 5 to 7 months.\n" +
                "Pigeon peas are rich in protein and are widely used as a staple food in many regions.\n",
            R.drawable.pigeonpeas),
        Crop("Pomegranate", "\nPomegranate thrives in semi-arid and subtropical regions.\n" +
                "It requires well-drained sandy loam or alluvial soil for optimal growth.\n" +
                "The ideal temperature range is between 25°C and 35°C, with dry conditions.\n" +
                "Pomegranates are drought-tolerant but benefit from regular irrigation during fruit development.\n" +
                "Propagation is done using seeds, cuttings, or air layering techniques.\n" +
                "High sunlight exposure enhances fruit quality and sugar content.\n" +
                "Common pests include aphids, fruit borers, and mealybugs.\n" +
                "Diseases such as bacterial blight and fungal leaf spots can affect production.\n" +
                "Fruits are harvested when they develop a deep red or yellowish color, depending on the variety.\n" +
                "Pomegranates are rich in antioxidants, vitamins, and are widely used in juices and medicinal products.\n",
            R.drawable.pomegranate),
        Crop("Rice", "\nRice requires a warm and humid climate for optimal growth.\n" +
                "It thrives in areas with temperatures ranging from 20°C to 35°C.\n" +
                "High rainfall or controlled irrigation is essential, with standing water during early growth stages.\n" +
                "Rice is best grown in clayey loam or alluvial soil that retains water.\n" +
                "Transplantation or direct seeding methods are used for cultivation.\n" +
                "Regular weeding and pest control are necessary for high yields.\n" +
                "Common pests include brown planthopper, rice stem borer, and leaf folder.\n" +
                "Diseases such as bacterial leaf blight and blast can affect rice crops.\n" +
                "Harvesting is done when the grains turn golden-yellow and have the right moisture content.\n" +
                "Rice is a staple food worldwide and is processed into various products like white rice, brown rice, and rice flour.\n",
            R.drawable.rice),
        Crop("Sugarcane", "\nSugarcane requires a long, warm growing season with abundant sunlight.\n" +
                "It thrives in temperatures between 20°C and 35°C.\n" +
                "Well-drained, fertile loamy or alluvial soil with good organic content is ideal.\n" +
                "High water requirements make irrigation essential in non-rainy regions.\n" +
                "Propagation is done through stem cuttings called 'setts'.\n" +
                "Regular fertilization with nitrogen, phosphorus, and potassium enhances growth.\n" +
                "Common pests include termites, top shoot borers, and white grubs.\n" +
                "Diseases like red rot, smut, and rust can affect sugarcane yield.\n" +
                "Harvesting is done when the stalks turn mature, usually after 10-18 months.\n" +
                "Used in sugar production, ethanol manufacturing, and by-products like bagasse for biofuel.\n",
            R.drawable.sugarcane),
        Crop("Wheat", "\nWheat grows best in cool weather with moderate temperatures between 10°C and 25°C.\n" +
                "It requires well-drained, fertile loamy or clay loam soil rich in organic matter.\n" +
                "Optimal rainfall ranges between 30 cm and 100 cm during the growing season.\n" +
                "Winter wheat needs vernalization, requiring cold temperatures for proper flowering.\n" +
                "Common wheat varieties include hard red, soft red, and durum wheat.\n" +
                "Proper irrigation is needed at critical growth stages such as tillering and grain filling.\n" +
                "Pests like aphids, armyworms, and termites can affect wheat crops.\n" +
                "Diseases such as rust, smut, and blight can reduce yield significantly.\n" +
                "Harvesting is done when the grains harden and moisture content is around 12-15%.\n" +
                "Wheat is a staple grain used in bread, pasta, and various processed foods.\n",
            R.drawable.wheat),
        Crop("Watermelon", "\nWatermelon thrives in hot, dry climates with temperatures between 25°C and 30°C.\n" +
                "It requires sandy or loamy soil with good drainage and a pH between 6.0 and 7.5.\n" +
                "Regular irrigation is essential, especially during flowering and fruit development.\n" +
                "Watermelons need full sunlight exposure for optimal growth and sweetness.\n" +
                "Propagation is done through direct seeding or transplanting seedlings.\n" +
                "Common varieties include Crimson Sweet, Sugar Baby, and Charleston Gray.\n" +
                "Mulching helps retain soil moisture and control weeds.\n" +
                "Pests like aphids, fruit flies, and spider mites can affect the crop.\n" +
                "Diseases such as powdery mildew, fusarium wilt, and anthracnose are common threats.\n" +
                "Harvesting is done when the fruit sounds hollow when tapped and the underside turns yellow.\n",
            R.drawable.watermelon),
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    context.startActivity(Intent(context, FormActivity::class.java))
                },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(56.dp) // Standard FAB size
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_custom_fab_icon), // Your custom image
                    contentDescription = "Add New Crop",
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "FarmWise 🌱",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(crops) { crop ->
                    CropCard(crop = crop) {
                        val intent = Intent(context, CropDetailActivity::class.java).apply {
                            putExtra("cropName", crop.name)
                            putExtra("cropDescription", crop.description)
                            putExtra("cropImageRes", crop.imageRes)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun CropCard(crop: Crop, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = crop.imageRes),
                contentDescription = crop.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = crop.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = crop.description,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
