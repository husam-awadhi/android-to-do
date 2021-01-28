package com.husam.to_do.ui.dashboard

//class DashboardFragment : Fragment() {
//
//    private lateinit var dashboardViewModel: DashboardViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        dashboardViewModel =
//            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
//        return root
//    }
//}