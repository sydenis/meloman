import java.util.*

const val KOPEEK_V_RUBLE = 100U

const val ITEM_PRIX = 100_00U
const val DISCOUNT_1K = 100_00U
const val DISCOUNT_10K = 5U
const val DISCOUNT_MELOMAN = 1U

const val SUM_ORDER_1K = 1_000_00U
const val SUM_ORDER_10K = 10_000_00U

const val DISCOUNT_MSG = "- использована скидка за"

fun main() {
    print("Имя пользователя: ")
    val userName = readln();

    print("куплено дисков: ")
    val itemCount = readln().toUInt();

    discountInfo(userName, itemCount);
}

fun isMeloman(userName: String): Boolean {
    return userName.uppercase(Locale.getDefault()) == "PETROV"
}

fun discountLevelBySum(sum: UInt): UInt {
    val result: UInt
    val discountState: UInt

    if (sum > SUM_ORDER_10K) {
        result = sum * DISCOUNT_10K / 100U
        discountState = SUM_ORDER_10K
    } else if (sum > SUM_ORDER_1K) {
        result = DISCOUNT_1K
        discountState = SUM_ORDER_1K
    } else {
        result = 0U
        discountState = result
    }

    if (discountState != 0U)
        println("$DISCOUNT_MSG сумму от ${sumToRuble(discountState)}, к оплате: ${sumToRuble(sum - result)}" )

    return sum - result
}

fun discountLevelByMeloman(userName: String, sum: UInt) {
    if (isMeloman(userName)) {
        val result: UInt = sum * (100U - DISCOUNT_MELOMAN) / 100U
        print("$DISCOUNT_MSG Меломана, к оплате: ${sumToRuble(result)}")
    }
}

fun discountInfo(userName: String, itemCount: UInt) {
    val orderSum = ITEM_PRIX * itemCount;
    println("Сумма покупки: ${sumToRuble(orderSum)}")

    val discountOnSum = discountLevelBySum(orderSum)

    discountLevelByMeloman(userName, discountOnSum)
}

fun sumToRuble(sum: UInt): String {
    return (sum / KOPEEK_V_RUBLE).toString() + "р."
}
