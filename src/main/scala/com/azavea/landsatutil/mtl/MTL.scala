package com.azavea.landsatutil.mtl

import scala.reflect._
import shapeless.syntax.typeable._
import shapeless._

class MTL(val group: Map[String, MtlGroup]) {
  def metadataFileInfo = group("METADATA_FILE_INFO")
  def productMetadata = group("PRODUCT_METADATA")
  def imageAttributes = group("IMAGE_ATTRIBUTES")
  def minMaxRadiance = group("MIN_MAX_RADIANCE")
  def minMaxReflectance = group("MIN_MAX_REFLECTANCE")
  def minMaxPixelValue = group("MIN_MAX_PIXEL_VALUE")
  def radiometricRescaling = group("RADIOMETRIC_RESCALING")
  def tirsThermalConstants = group("TIRS_THERMAL_CONSTANTS")
  def projectionParameters = group("PROJECTION_PARAMETERS")
}

class MtlGroup(val name: String, val fields: Map[String, Any]) {
  def apply[T: Typeable](fieldName: String): Option[T] = {
    // seems weird, but safely casting primitive and references is actually tricky
    fields.get(fieldName).flatMap(_.cast[T])
  }

  override def toString = s"MtlGroup($name)"
}