package th.ac.comsci.job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var loan: EditText? = null
    private var interest: EditText? = null
    private var period: EditText? = null

    private var bt_save: Button? = null
    private var bt_reset: Button? = null
    private var bt_show: Button? = null
    private var bt_shownew: Button? = null

    private var databaseHelper: DatabaseHelper? = null
    private var result: TextView? = null
    private var arrayList: ArrayList<String>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)
        result = findViewById(R.id.text_result) as TextView


        loan = findViewById(R.id.i_lona) as EditText
        interest = findViewById(R.id.i_inter) as EditText
        period = findViewById(R.id.i_period) as EditText

        bt_save = findViewById(R.id.bt_save) as Button
        bt_reset = findViewById(R.id.bt_reset) as Button
        bt_show = findViewById(R.id.bt_show) as Button
        bt_shownew = findViewById(R.id.bt_shownew) as Button


        bt_save!!.setOnClickListener {
            databaseHelper!!.addGradeDetail(loan!!.text.toString(),interest!!.text.toString(),period!!.text.toString())

            loan!!.setText("")
            interest!!.setText("")
            period!!.setText("")




            Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()
        }

        bt_reset!!.setOnClickListener {
            databaseHelper!!.resetDatabase()
        }

        bt_show!!.setOnClickListener {
            arrayList = databaseHelper!!.allSubjectList
            result!!.text = ""
            var ord = 1
            for (i in arrayList!!.indices) {
                result!!.text = result!!.text.toString() + (ord++) + ". " + arrayList!![i] + "\n"
            }
        }


        bt_shownew!!.setOnClickListener {
            arrayList = databaseHelper!!.allSubjectList1
            result!!.text = ""
            var ord = 1
            for (i in arrayList!!.indices) {
                result!!.text = result!!.text.toString() + (ord++) + ". " + arrayList!![i] + "\n"
            }
        }




    }
}
