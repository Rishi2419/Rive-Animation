package com.example.riveanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import app.rive.runtime.kotlin.core.Rive
import com.example.riveanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var statemachineName = "Login Machine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Rive.init(this)

        binding.email.setOnFocusChangeListener{ view , b ->
            if (b){
                binding.loginCharacter.controller.setBooleanState(statemachineName,"isChecking",true)
            }
            else{
                binding.loginCharacter.controller.setBooleanState(statemachineName,"isChecking",false)

            }

        }
        binding.password.setOnFocusChangeListener{ view , b ->
            if (b){
                binding.loginCharacter.controller.setBooleanState(statemachineName,"isHandsUp",true)
            }
            else{
                binding.loginCharacter.controller.setBooleanState(statemachineName,"isHandsUp ",false)

            }

        }
        binding.email.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                try {
                    binding.loginCharacter.controller.setNumberState(statemachineName,"numLook",p0!!.length.toFloat())
                } catch (e: Exception) {
                    TODO("Not yet implemented")
                }
            }

        })

        binding.button.setOnClickListener{

            binding.password.clearFocus()

            Handler(mainLooper).postDelayed({
                if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty() && (binding.email.text.toString()=="sahurishikesh1908@gmail.com" && binding.password.text.toString()=="rishi@19") ){
                    binding.loginCharacter.controller.fireState(statemachineName,"trigSuccess")

                }
                else
                {
                    binding.loginCharacter.controller.fireState(statemachineName,"trigFail")
                }
            },2000)

        }
    }
}