package io.github.nillerr.ooxml.databind

class ObjectTableMapperOptions {
    var cellValueSerializers: MutableList<CellValueSerializer> = mutableListOf(
        NullCellValueSerializer(),
        StringCellValueSerializer(),
        NumberCellValueSerializer(),
        BooleanCellValueSerializer(),
        InstantCellValueSerializer(),
        LocalDateCellValueSerializer(),
        LocalTimeCellValueSerializer(),
        LocalDateTimeCellValueSerializer(),
        HyperlinkCellValueSerializer(),
        URLCellValueSerializer(),
        URICellValueSerializer(),
    )

    var columnNameResolver: ColumnNameResolver = DefaultColumnNameResolver()

    var dataFormatResolver: DataFormatResolver = DefaultDataFormatResolver()
}
