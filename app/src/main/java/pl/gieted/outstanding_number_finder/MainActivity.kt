package pl.gieted.outstanding_number_finder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.gieted.outstanding_number_finder.ui.main.NumbersInputFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NumbersInputFragment())
                .commitNow()
        }
    }
}
