# radial-quick-actions
An android compose module to create a quick popup of buttons in a radial layout

## usage

create class that will be used for passing data
```
class act(name: String, onSelect:()->Unit) : QuickAction(name,onSelect)
```

create view model
```
val vm by remember {
                    mutableStateOf(RadialQuickActionViewModel(
                        listOf(
                            act("0",{}),
                            act("1",{}),
                            act("2",{}),
                            act("3",{}),
                            act("4",{}),
                        )
                    ))
                }
```

create trigger area that will be used for interaction
```
RadialQuickActionTrigger(viewModel = vm)
```

create visual for the quick action item
```
@Composable
fun QuickActionItem(m: Modifier, a: act, s: Boolean){
  // draw something
}
```

use quick action item in RadialQuickActionVisual
```
RadialQuickActionVisual(
                        viewModel = vm,
                        actionItem = {m,a,s ->
                            Box(m) {
                                QuickActionItem(m, a, s)
                            }
                        }
                    )
```
