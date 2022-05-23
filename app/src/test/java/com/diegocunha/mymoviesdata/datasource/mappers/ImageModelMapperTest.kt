package com.diegocunha.mymoviesdata.datasource.mappers

import com.diegocunha.mymoviesdata.datasource.mapper.ImageMapper
import kotlin.test.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImageModelMapperTest {

    @Test
    fun `WHEN Image Mapper is called THEN should map url to valid image`() {
        val imageMapper = ImageMapper()
        val result = imageMapper("posterPath")
        assertTrue(result.contains("posterPath"))
    }
}