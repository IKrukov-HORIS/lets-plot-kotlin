package com.jetbrains.datalore.plot.dsl

import com.jetbrains.datalore.plot.GeomKind
import com.jetbrains.datalore.plot.LayerAssert
import com.jetbrains.datalore.plot.StatKind
import com.jetbrains.datalore.plot.dsl.Geom.point
import com.jetbrains.datalore.plot.dsl.stat.stat_density
import org.junit.Test

class StatLayerTest {

    @Test
    fun `stat with default geom`() {
        val l = stat_density({ fill = "F" }, color = "C")

        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("color", "C")
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.AREA)
            .noMapping().noParameters()
    }

    @Test
    fun `stat with overridden geom`() {
        val l = stat_density({ x = "X"; fill = "F" }, color = "C", geom = point(alpha = 0.5))

        LayerAssert.assertThat(l)
            .aes("x", "X")
            .aes("fill", "F")
            .parameter("color", "C")
            .parameter("alpha", 0.5)
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()

        LayerAssert.assertThat(l)
            .geom()
            .kind(GeomKind.POINT)
            .noMapping()
            .parameter("alpha", 0.5)
    }

    @Test
    fun `stat with parameter`() {
        val l = stat_density({ fill = "F" }, kernel = "gaussian", color = "C")
        LayerAssert.assertThat(l)
            .aes("fill", "F")
            .parameter("kernel", "gaussian")
            .parameter("color", "C")
            .stat()
            .kind(StatKind.DENSITY)
            .noMapping().noParameters()
    }
}