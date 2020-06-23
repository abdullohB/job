package th.ac.comsci.job

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class DatabaseHelper  (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

//แบบคงที่
    val allSubjectList: ArrayList<String>
        @SuppressLint("Recycle")
        get() {

            val gradesArrayList = ArrayList<String>()
            val gradesArrayList1 = ArrayList<String>()


            var s_m_loan = 0.0 //จำนวนเงินทั้งหมด
            var s_m_inter = 0.0 //อัตราดอกเบี้ย
            var s_m_period= 0.0 //s_m_period
            var loan = ""
            var interest = ""
            var period = ""

            val selectQuery = "SELECT * FROM $TABLE_GRADES"

            val db = this.readableDatabase

            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {

                    loan = c.getString(c.getColumnIndex(LOAN))
                    interest = c.getString(c.getColumnIndex(INTEREST))
                    period = c.getString(c.getColumnIndex(PERIOD))

                    gradesArrayList.add(
                        " loan: " + loan + "  interest: " + interest + "   period: '" +
                                period
                    )

                    s_m_inter = (loan.toDouble()*interest.toDouble())/100
                    s_m_loan = loan.toInt()+(s_m_inter*period.toDouble()/12)
                    s_m_period = s_m_loan/period.toDouble()
                    gradesArrayList.add("Result loan : " + s_m_loan.toInt())
                    gradesArrayList.add("Result interest : " + s_m_inter.toInt())
                    gradesArrayList.add("Result period : " + s_m_period.toInt())
                    gradesArrayList.add("______________________________")

                } while (c.moveToNext())
                Log.d("array", gradesArrayList.toString())

            }

            return gradesArrayList
        }

//แบบธนาคาร
    val allSubjectList1: ArrayList<String>
        @SuppressLint("Recycle")
        get() {

            val gradesArrayList = ArrayList<String>()
            val gradesArrayList1 = ArrayList<String>()
            var p = 0.0

            var s_m_loan = 0.0 //จำนวนเงินทั้งหมด
            var s_m_inter = 0.0 //อัตราดอกเบี้ย
            var s_m_period= 0.0 //s_m_period
            var loan = ""
            var interest = ""
            var period = ""

            val selectQuery = "SELECT * FROM $TABLE_GRADES"

            val db = this.readableDatabase

            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {

                    loan = c.getString(c.getColumnIndex(LOAN))
                    interest = c.getString(c.getColumnIndex(INTEREST))
                    period = c.getString(c.getColumnIndex(PERIOD))

                    gradesArrayList.add(
                        " loan: " + loan + "  interest: " + interest + "   period: '" +
                                period
                    )

                    println("Hello")
//                   val x =arrayof(1)
//
//                    for (i  in 0..period.toInt())
//                    {
//                       var s = i+1
//                        gradesArrayList.add(" "+s)
//                    }


                } while (c.moveToNext())
                Log.d("array", gradesArrayList.toString())

            }

            return gradesArrayList
        }





    init {

        Log.d("table", CREATE_TABLE_GRADES)
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GRADES)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        onCreate(db)
    }

    fun resetDatabase() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_GRADES'")
        db.execSQL(CREATE_TABLE_GRADES)
    }

    fun addGradeDetail(
        loan: String,
        interest: String,
        period: String
    ): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(LOAN, loan)
        values.put(INTEREST, interest)
        values.put(PERIOD, period)

        // insert row in students table


        return db.insert(TABLE_GRADES, null, values)
    }


    companion object {
        var DATABASE_NAME = "grade_database"
        private val DATABASE_VERSION = 1
        private val TABLE_GRADES = "grades"
        private val LOAN = "loan"
        private val INTEREST = "interest"
        private val PERIOD = "period"



        private val CREATE_TABLE_GRADES = ("CREATE TABLE " + TABLE_GRADES +
                "(" + LOAN + " TEXT ," + INTEREST +
                " TEXT, " + PERIOD + " TEXT); " )
    }
}









