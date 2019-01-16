package pereira.agnaldo.iot001.extensions


fun Collection<*>?.isNotNullAndNotEmpty(): Boolean {
    return this.orEmpty().filterNotNull().isNotEmpty()
}

fun Collection<*>?.sizeOrZero(): Int{
    return this.orEmpty().size
}