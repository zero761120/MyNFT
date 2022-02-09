package com.louis.showNft.paging

import androidx.paging.DataSource

/**
 * DataSource Factory for converting model to another type.
 */
class DataSourceConverterFactory<Key, Value, ToValue>(
    private val factory: DataSource.Factory<Key, Value>,
    private val convert: (Value) -> ToValue
) : DataSource.Factory<Key, ToValue>() {

    override fun create(): DataSource<Key, ToValue> {
        return factory.create().map { convert(it) }
    }

}
