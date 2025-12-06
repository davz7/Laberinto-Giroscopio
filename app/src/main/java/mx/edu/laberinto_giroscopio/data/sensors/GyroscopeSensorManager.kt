package mx.edu.laberinto_giroscopio.data.sensors

import android.content.Context
import android.hardware.*

class GyroscopeSensorManager(
    context: Context,
    private val onRotation: (x: Float, y: Float, z: Float) -> Unit
) : SensorEventListener {
    private val sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    fun start() {
        sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_GAME)
    }
    fun stop() {
        sm.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { onRotation(it.values[0], it.values[1], it.values[2]) }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
