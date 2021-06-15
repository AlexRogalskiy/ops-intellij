package com.nanovms.ops.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.nanovms.ops.ui.DropdownDialog
import com.nanovms.ops.Service
import com.nanovms.ops.command.StopInstanceCommand

class StopInstanceAction : BaseAction() {
    override fun isEnabled(e: AnActionEvent, ops: Service): Boolean {
        return ops.hasInstances()
    }

    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let {
            val ops = service<Service>()
            val dialog = DropdownDialog(ops.listInstances(), null)
            val isOK = dialog.showAndGet()
            if (isOK) {
                val instanceName = dialog.model.selectedItem as String
                StopInstanceCommand(it, instanceName).execute()
            }
        }
    }
}