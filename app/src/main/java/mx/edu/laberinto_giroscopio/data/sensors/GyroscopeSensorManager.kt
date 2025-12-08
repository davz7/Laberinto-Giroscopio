package mx.edu.laberinto_giroscopio.data.sensors

import android.content.Context
import android.hardware.*

class GyroscopeSensorManager(
    context: Context,
    private val onRotation: (Float, Float, Float) -> Unit,
    private val onUnavailable: () -> Unit
) : SensorEventListener {

    private val sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    private val sensitivity = 5f

    fun start() {
        if (gyro == null) {
            onUnavailable()
            return
        }
        sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop() {
        sm.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0] * sensitivity
            val y = it.values[1] * sensitivity
            val z = it.values[2] * sensitivity

            onRotation(x, y, z)
        }
    }
}
